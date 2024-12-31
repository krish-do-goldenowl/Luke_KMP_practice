/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.samples.apps.sunflower

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.auth.api.identity.Identity
import com.google.samples.apps.sunflower.presentation.google_sign_in.GoogleAuthUIClient
import com.google.samples.apps.sunflower.ui.SunflowerTheme
import dagger.hilt.android.AndroidEntryPoint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ShareCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.samples.apps.sunflower.compose.Screen
import com.google.samples.apps.sunflower.compose.gallery.GalleryScreen
import com.google.samples.apps.sunflower.compose.home.HomeScreen
import com.google.samples.apps.sunflower.compose.plantdetail.PlantDetailsScreen
import com.google.samples.apps.sunflower.compose.profile.ProfileScreen
import com.google.samples.apps.sunflower.compose.signin.SignInScreen
import com.google.samples.apps.sunflower.compose.signup.SignUpScreen
import com.google.samples.apps.sunflower.presentation.sign_in_email_password.SignInEmailPassword
import com.google.samples.apps.sunflower.viewmodels.PlantListViewModel
import com.google.samples.apps.sunflower.viewmodels.UserViewModel
import kotlinx.coroutines.launch


fun mToast(context: Context, message: String){
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}

@AndroidEntryPoint
class GardenActivity : ComponentActivity() {

    private val googleAuthUiClient by lazy {
        GoogleAuthUIClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }


    private val signInEmailPassword by lazy {
        SignInEmailPassword()
    }

    private val userViewModel by viewModels<UserViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Displaying edge-to-edge
        enableEdgeToEdge()
        setContent {
            SunflowerTheme {
                val activity = (LocalContext.current as Activity)
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Screen.SignIn.route) {
                    composable(route = Screen.SignIn.route) {
//                        val viewModel = viewModel<UserViewModel>()
                        val user by userViewModel.user.collectAsStateWithLifecycle()

                        LaunchedEffect(key1 = Unit) {
                            val cachedUser = googleAuthUiClient.getSignedInUser()
                            if (cachedUser != null && cachedUser.userId.isNotEmpty()){
                                userViewModel.getUser(cachedUser)
                            }
                        }
                        val launcher = rememberLauncherForActivityResult(
                            contract = ActivityResultContracts.StartIntentSenderForResult(),
                            onResult = {result ->
                                if (result.resultCode == RESULT_OK) {
                                    lifecycleScope.launch {
                                        val signInResult = googleAuthUiClient.signInWithIntent(
                                            intent = result.data ?: return@launch
                                        )
                                        userViewModel.onSignInResult(signInResult)
                                        print("Data: ${signInResult.data}, Message: ${signInResult.errorMessage}")
                                    }
                                }

                            }
                        )
                        LaunchedEffect(key1 = user.userId.isNotEmpty()) {
                            if (user.userId.isNotEmpty()){
                                mToast(applicationContext, "Sign In Successful")
                                navController.navigate(Screen.Home.route) {
                                    popUpTo(Screen.SignIn.route) { inclusive = true }
                                }
//                                userViewModel.resetUser()
                            }
                        }
                        SignInScreen(
                            onLoginClick = { email, password ->
                                if(email.isEmpty() || password.isEmpty()){
                                    mToast(applicationContext, "Please fill in all information to login")
                                } else {
                                    lifecycleScope.launch {
                                        val result = signInEmailPassword.signInWithEmail(email.trim().lowercase(), password)
                                        userViewModel.onSignInResult(result)
                                        if(result.isError) {
                                            mToast(applicationContext, "Sign In result error: ${result.errorMessage}")
                                        }
                                    }
                                }
                            },
                            onSignInGoogleClick = {
                                lifecycleScope.launch {
                                    val signInIntentSender = googleAuthUiClient.signIn()
                                    launcher.launch(
                                        IntentSenderRequest.Builder(
                                            signInIntentSender ?: return@launch
                                        ).build()
                                    )
                                }
                            },
                            onSignUpClick = {
                                navController.navigate(Screen.SignUp.route)
                            },
                        )
                    }
                    composable(route = Screen.SignUp.route) {
                        SignUpScreen(
                            onBackToLoginClick = {
                                navController.popBackStack()
                            },
                            onSignUpClick = { email, password, confirmPass ->
                                if(email.isEmpty() || password.isEmpty() || confirmPass.isEmpty()){
                                    mToast(applicationContext, "Please fill in all information to login")
                                } else if(password != confirmPass) {
                                    mToast(applicationContext, "Confirm password incorrect")
                                } else {
                                    lifecycleScope.launch {
                                        val result = signInEmailPassword.createAccountWithEmail(
                                            email.trim().lowercase(), password
                                        )
                                        userViewModel.onSignInResult(result)
                                        if(result.isSuccess) {
                                            mToast(applicationContext, "Sign Up successful email: ${result.data?.email}")
                                            navController.popBackStack()
                                        } else {
                                            mToast(applicationContext,"Sign Up result error: ${result.errorMessage}")
                                        }
                                    }
                                }
                            }
                        )
                    }
                    composable(route = Screen.Home.route) {
//                        val viewModel = viewModel<UserViewModel>()
                        HomeScreen(
                            onPlantClick = {
                                navController.navigate(
                                    Screen.PlantDetail.createRoute(
                                        plantId = it.plantId
                                    )
                                )
                            },
                            onLogout = {
                                lifecycleScope.launch {
                                    googleAuthUiClient.signOut()
                                    userViewModel.resetUser()
                                    navController.navigate(Screen.SignIn.route) {
                                        popUpTo(Screen.Home.route) { inclusive = true }
                                    }
                                }
                            },
                            onShowProfile = {
                                navController.navigate(Screen.Profile.route)
                            }
                        )
                    }
                    composable(
                        route = Screen.PlantDetail.route,
                        arguments = Screen.PlantDetail.navArguments
                    ) {
                        PlantDetailsScreen(
                            onBackClick = { navController.navigateUp() },
                            onShareClick = {
                                createShareIntent(activity, it)
                            },
                            onGalleryClick = {
                                navController.navigate(
                                    Screen.Gallery.createRoute(
                                        plantName = it.name
                                    )
                                )
                            }
                        )
                    }
                    composable(
                        route = Screen.Gallery.route,
                        arguments = Screen.Gallery.navArguments
                    ) {
                        GalleryScreen(
                            onPhotoClick = {
                                val uri = Uri.parse(it.user.attributionUrl)
                                val intent = Intent(Intent.ACTION_VIEW, uri)
                                activity.startActivity(intent)
                            },
                            onUpClick = {
                                navController.navigateUp()
                            })
                    }
                    composable(route = Screen.Profile.route) {
                        ProfileScreen(
                            onSave = {user ->
                                lifecycleScope.launch {
                                    userViewModel.setUser(user, updateFirestore = true)
                                    navController.popBackStack()
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

// Helper function for calling a share functionality.
// Should be used when user presses a share button/menu item.
private fun createShareIntent(activity: Activity, plantName: String) {
    val shareText = activity.getString(R.string.share_text_plant, plantName)
    val shareIntent = ShareCompat.IntentBuilder(activity)
        .setText(shareText)
        .setType("text/plain")
        .createChooserIntent()
        .addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT or Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
    activity.startActivity(shareIntent)
}

