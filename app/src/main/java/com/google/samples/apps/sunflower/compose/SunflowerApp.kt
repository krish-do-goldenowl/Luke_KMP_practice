/*
 * Copyright 2023 Google LLC
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

package com.google.samples.apps.sunflower.compose

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.lifecycle.lifecycleScope
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ShareCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.samples.apps.sunflower.R
import com.google.samples.apps.sunflower.compose.gallery.GalleryScreen
import com.google.samples.apps.sunflower.compose.home.HomeScreen
import com.google.samples.apps.sunflower.compose.plantdetail.PlantDetailsScreen
import com.google.samples.apps.sunflower.compose.signin.SignInScreen
import com.google.samples.apps.sunflower.presentation.google_sign_in.GoogleAuthUIClient

//
//@Composable
//fun SunflowerApp() {
//    val navController = rememberNavController()
//    SunFlowerNavHost(
//        navController = navController
//    )
//}
//
//@Composable
//fun SunFlowerNavHost(
//    navController: NavHostController
//) {
//    val activity = (LocalContext.current as Activity)
//    NavHost(navController = navController, startDestination = Screen.SignIn.route) {
//        composable(route = Screen.SignIn.route) {
//            SignInScreen(
//                googleAuthUiClient = googleAuthUiClient,
//                onSignInClick = {
//                    navController.navigate(Screen.Home.route) {
//                        popUpTo(Screen.SignIn.route) { inclusive = true } // Xóa Login khỏi Back Stack
//                    }
//                }
//            )
//        }
//        composable(route = Screen.Home.route) {
//            HomeScreen(
//                onPlantClick = {
//                    navController.navigate(
//                        Screen.PlantDetail.createRoute(
//                            plantId = it.plantId
//                        )
//                    )
//                },
//                onLogout = {
//                    navController.navigate(Screen.SignIn.route)
//                },
//            )
//        }
//        composable(
//            route = Screen.PlantDetail.route,
//            arguments = Screen.PlantDetail.navArguments
//        ) {
//            PlantDetailsScreen(
//                onBackClick = { navController.navigateUp() },
//                onShareClick = {
//                    createShareIntent(activity, it)
//                },
//                onGalleryClick = {
//                    navController.navigate(
//                        Screen.Gallery.createRoute(
//                            plantName = it.name
//                        )
//                    )
//                }
//            )
//        }
//        composable(
//            route = Screen.Gallery.route,
//            arguments = Screen.Gallery.navArguments
//        ) {
//            GalleryScreen(
//                onPhotoClick = {
//                    val uri = Uri.parse(it.user.attributionUrl)
//                    val intent = Intent(Intent.ACTION_VIEW, uri)
//                    activity.startActivity(intent)
//                },
//                onUpClick = {
//                    navController.navigateUp()
//                })
//        }
//    }
//}

