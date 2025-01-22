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

package com.google.samples.apps.sunflower.compose.dashboard

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.samples.apps.sunflower.R
import com.google.samples.apps.sunflower.compose.Screen
import com.google.samples.apps.sunflower.compose.homebank.HomeBankScreen
import com.google.samples.apps.sunflower.compose.mycards.MyCardsScreen
import com.google.samples.apps.sunflower.compose.settings.SettingsCallbacks
import com.google.samples.apps.sunflower.compose.settings.SettingsScreen
import com.google.samples.apps.sunflower.compose.statistics.StatisticsScreen
import com.google.samples.apps.sunflower.compose.transactionhistory.TransactionHistoryScreen
import com.google.samples.apps.sunflower.ui.SunflowerTheme
import com.google.samples.apps.sunflower.ui.md_theme_light_primary
import com.google.samples.apps.sunflower.viewmodels.PlantListViewModel

enum class BottomNavigationItem(
    val route: String,
    val title: String,
    val selectedIcon: Int,
    val unselectedIcon: Int,
) {
    HOME(
        route = "home",
        title = "Home",
        selectedIcon = R.drawable.home_selected,
        unselectedIcon = R.drawable.home,
    ),
    MY_CARDS(
        route = "myCards",
        title = "My Cards",
        selectedIcon = R.drawable.my_cards_selected,
        unselectedIcon = R.drawable.my_cards,
    ),
    STATISTICS(
        route = "statistics",
        title = "Statistics",
        selectedIcon = R.drawable.statistics_selected,
        unselectedIcon = R.drawable.statistics,
    ),
    SETTINGS(
        route = "settings",
        title = "Settings",
        selectedIcon = R.drawable.settings_selected,
        unselectedIcon = R.drawable.settings,
    ),
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun DashboardScreen(
    modifier: Modifier = Modifier,
    viewModel: PlantListViewModel = hiltViewModel(),
    settingsCallbacks: SettingsCallbacks,
    onAddNewCard: () -> Unit,
    onSearchTransaction: () -> Unit,
) {
    val navController = rememberNavController()
    var selectedItemIndex by rememberSaveable{
        mutableIntStateOf(0)
    }
    Scaffold(
        modifier = Modifier.safeDrawingPadding(),
        bottomBar = {
            NavigationBar {
                BottomNavigationItem.entries.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedItemIndex == index,
                        onClick = {
                            var currentRouteIndex = selectedItemIndex
                            selectedItemIndex = index
                            navController.navigate(item.route) {
                                popUpTo(BottomNavigationItem.entries[currentRouteIndex].route) { inclusive = true }
                            }
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedTextColor = md_theme_light_primary,
                            unselectedTextColor = Color(0xFF8B8B94),
                            indicatorColor = Color.Transparent,
                        ),
                        label = {
                            Text(
                                text = item.title,
                                fontSize = 10.sp
                            )
                        },
                        icon = {
                            Image(
                                painter = painterResource(
                                    id = if(selectedItemIndex == index)
                                        item.selectedIcon
                                    else
                                        item.unselectedIcon
                                ),
                                contentDescription = "Email",
                                modifier = Modifier.width(18.dp).height(18.dp)
                            )
                        },
                        modifier = Modifier.padding(5.dp, 5.dp, 5.dp, 0.dp)
                    )
                }
            }
        }
    ) { contentPadding ->
        Navigation(
            navController = navController,
            modifier = Modifier.padding(contentPadding),
            settingsCallbacks = settingsCallbacks,
            onAddNewCard = onAddNewCard,
            onSearchTransaction = onSearchTransaction,
        )
    }
}

@Composable
fun Navigation(
    navController: NavHostController,
    modifier: Modifier,
    settingsCallbacks: SettingsCallbacks,
    onAddNewCard: () -> Unit,
    onSearchTransaction: () -> Unit,
) {
    NavHost(
        navController = navController,
        startDestination = BottomNavigationItem.HOME.route,
        modifier = modifier,
    ) {
        composable(BottomNavigationItem.HOME.route) {
            HomeBankScreen(
                onSeeAll = {
                    navController.navigate(Screen.TransactionHistory.route)
                }
            )
        }
        composable(BottomNavigationItem.MY_CARDS.route) {
            MyCardsScreen(onAddNewCard = onAddNewCard)
        }
        composable(BottomNavigationItem.STATISTICS.route) {
            StatisticsScreen()
        }
        composable(BottomNavigationItem.SETTINGS.route) {
            SettingsScreen(
                settingsCallbacks = settingsCallbacks
            )
        }
        composable(Screen.TransactionHistory.route) {
            TransactionHistoryScreen(
                onBack = {
                    navController.popBackStack()
                },
                onSearchTransaction = onSearchTransaction
            )
        }
    }
}

@Preview
@Composable
private fun DashboardScreenPreview() {
    SunflowerTheme {
        DashboardScreen(
            settingsCallbacks = SettingsCallbacks(
                onMyProfileClick = {},
                onPrivacyPolicyClick = {},
                onChangePasswordClick = {},
                onLanguageClick = {},
            ),
            onAddNewCard = {},
            onSearchTransaction = {},
        )
    }
}
