/*
 * Copyright 2025 Google LLC
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

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.samples.apps.sunflower.compose.Screen
import com.google.samples.apps.sunflower.compose.addnewcard.AddNewCardScreen
import com.google.samples.apps.sunflower.compose.allcards.AllCardsScreen
import com.google.samples.apps.sunflower.compose.changepassword.ChangePasswordScreen
import com.google.samples.apps.sunflower.compose.dashboard.DashboardScreen
import com.google.samples.apps.sunflower.compose.editprofile.EditProfileScreen
import com.google.samples.apps.sunflower.compose.language.LanguageScreen
import com.google.samples.apps.sunflower.compose.onboarding1.OnboardingScreen
import com.google.samples.apps.sunflower.compose.profilebank.ProfileBankScreen
import com.google.samples.apps.sunflower.compose.requestmoney.RequestMoneyScreen
import com.google.samples.apps.sunflower.compose.searchtransaction.SearchTransactionScreen
import com.google.samples.apps.sunflower.compose.sendmoney.SendMoneyScreen
import com.google.samples.apps.sunflower.compose.settings.SettingsCallbacks
import com.google.samples.apps.sunflower.compose.signinbank.SignInBankScreen
import com.google.samples.apps.sunflower.compose.signupbank.SignUpBankScreen
import com.google.samples.apps.sunflower.compose.termandcondition.TermAndConditionScreen
import com.google.samples.apps.sunflower.ui.SunflowerTheme
import dagger.hilt.android.AndroidEntryPoint

fun mToast(context: Context, message: String){
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}


@AndroidEntryPoint
class BankPickActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Displaying edge-to-edge
        enableEdgeToEdge()
        setContent {
            SunflowerTheme {
                val activity = (LocalContext.current as Activity)
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Screen.Dashboard.route) {
                    composable(route = Screen.Onboarding.route) {
                        OnboardingScreen(
                            onNext = {
                                navController.navigate(Screen.SignInBank.route) {
                                    popUpTo(Screen.Onboarding.route) { inclusive = true }
                                }
                            },
                        )
                    }
                    composable(route = Screen.SignInBank.route) {
                        SignInBankScreen(
                            onLoginClick = {a,b ->
                                navController.navigate(Screen.Dashboard.route) {
                                    popUpTo(Screen.SignInBank.route) { inclusive = true }
                                }
                            },
                            onSignUpClick = {
                                navController.navigate(Screen.SignUpBank.route)
                            },
                        )
                    }
                    composable(route = Screen.SignUpBank.route) {
                        SignUpBankScreen(
                            onSignUpClick = {_,_,_,_ ->},
                            onBack = {
                                navController.popBackStack()
                            },
                        )
                    }
                    composable(route = Screen.Dashboard.route) {
                        DashboardScreen(
                            settingsCallbacks = SettingsCallbacks(
                                onMyProfileClick = {
                                    navController.navigate(Screen.ProfileBank.route)
                                },
                                onPrivacyPolicyClick = {
                                    navController.navigate(Screen.TermAndCondition.route)
                                },
                                onChangePasswordClick = {
                                    navController.navigate(Screen.ChangePassword.route)
                                },
                                onLanguageClick = {
                                    navController.navigate(Screen.Language.route)
                                },
                            ),
                            onAddNewCard = {
                                navController.navigate(Screen.AddNewCard.route)
                            },
                            onSearchTransaction = {
                                navController.navigate(Screen.SearchTransaction.route)
                            }
                        )
                    }
                    composable(route = Screen.ProfileBank.route) {
                        ProfileBankScreen(
                            onBack = {
                                navController.popBackStack()
                            },
                            onEditProfile = {
                                navController.navigate(Screen.EditProfile.route)
                            },
                            onBanksAndCards = {
                                navController.navigate(Screen.AllCards.route)
                            },
                            onRequestMoney = {
                                navController.navigate(Screen.SendMoney.route)
                            }
                        )
                    }
                    composable(route = Screen.EditProfile.route) {
                        EditProfileScreen(
                            onBack = {
                                navController.popBackStack()
                            },
                        )
                    }
                    composable(route = Screen.TermAndCondition.route) {
                        TermAndConditionScreen(
                            onBack = {
                                navController.popBackStack()
                            },
                        )
                    }
                    composable(route = Screen.ChangePassword.route) {
                        ChangePasswordScreen(
                            onBack = {
                                navController.popBackStack()
                            },
                        )
                    }
                    composable(route = Screen.Language.route) {
                        LanguageScreen(
                            onBack = {
                                navController.popBackStack()
                            },
                        )
                    }
                    composable(route = Screen.AddNewCard.route) {
                        AddNewCardScreen(
                            onBack = {
                                navController.popBackStack()
                            },
                        )
                    }
                    composable(route = Screen.AllCards.route) {
                        AllCardsScreen(
                            onBack = {
                                navController.popBackStack()
                            },
                            onAddCard = {
                                navController.navigate(Screen.AddNewCard.route)
                            }
                        )
                    }
                    composable(route = Screen.SearchTransaction.route) {
                        SearchTransactionScreen(
                            onBack = {
                                navController.popBackStack()
                            },
                        )
                    }
                    composable(route = Screen.SendMoney.route) {
                        SendMoneyScreen(
                            onBack = {
                                navController.popBackStack()
                            },
                            onSendMoney = {
                                navController.navigate(Screen.RequestMoney.route)
                            }
                        )
                    }
                    composable(route = Screen.RequestMoney.route) {
                        RequestMoneyScreen(
                            onBack = {
                                navController.popBackStack()
                            },
                        )
                    }
                }
            }
        }
    }
}