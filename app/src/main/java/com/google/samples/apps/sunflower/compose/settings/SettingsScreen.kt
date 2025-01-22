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

package com.google.samples.apps.sunflower.compose.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.samples.apps.sunflower.R
import com.google.samples.apps.sunflower.compose.utils.BankTopAppBar

data class SettingsCallbacks(
    val onMyProfileClick: () -> Unit,
    val onPrivacyPolicyClick: () -> Unit,
    val onChangePasswordClick: () -> Unit,
    val onLanguageClick: () -> Unit,
)

@Composable
fun SettingsScreen(
    settingsCallbacks: SettingsCallbacks,
) {
    var biometric by remember {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
            BankTopAppBar(
                title = "Settings",
                iconSource = R.drawable.log_out_icon,
                hasBackIcon = false,
            )
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(20.dp, contentPadding.calculateTopPadding(), 20.dp, 0.dp)
                .fillMaxSize(),
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "General",
                fontSize = 14.sp,
                lineHeight = 14.sp,
                color = Color(0xFFA2A2A7)
            )
            Spacer(modifier = Modifier.height(31.dp))
            SettingButton(
                title = "Language",
                subTitle = "English",
                onClick = settingsCallbacks.onLanguageClick,
            )
            Spacer(modifier = Modifier.height(22.dp))
            SettingButton(
                title = "My Profile",
                onClick = settingsCallbacks.onMyProfileClick,
            )
            Spacer(modifier = Modifier.height(22.dp))
            SettingButton(
                title = "Contact Us",
            )
            Spacer(modifier = Modifier.height(31.dp))
            Text(
                text = "Security",
                fontSize = 14.sp,
                lineHeight = 14.sp,
                color = Color(0xFFA2A2A7)
            )
            Spacer(modifier = Modifier.height(31.dp))
            SettingButton(
                title = "Change Password",
                onClick = settingsCallbacks.onChangePasswordClick,
            )
            Spacer(modifier = Modifier.height(22.dp))
            SettingButton(
                title = "Privacy Policy",
                onClick = settingsCallbacks.onPrivacyPolicyClick
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = "Choose what data you share with us",
                fontSize = 12.sp,
                lineHeight = 12.sp,
                color = Color(0xFF7E848D)
            )
            Spacer(modifier = Modifier.height(31.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Biometric",
                    fontSize = 14.sp,
                    lineHeight = 14.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFFFFFFFF),
                    modifier = Modifier.weight(1F)
                )
                Switch(
                    checked = biometric,
                    onCheckedChange = {value -> biometric = value},
                    colors = SwitchDefaults.colors(
                        uncheckedBorderColor = Color.Transparent,
                        uncheckedThumbColor = Color.White,
                        uncheckedIconColor = Color.White,
                        uncheckedTrackColor = Color(0xFFAAAFB7),
                    )
                )
            }
        }
    }
}

@Composable
private fun SettingButton(
    title: String,
    subTitle: String? = null,
    onClick: () -> Unit = {},
) {
    Column {
        Button(
            onClick = onClick,
            contentPadding = PaddingValues(0.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
            ),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title,
                    fontSize = 14.sp,
                    lineHeight = 14.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFFFFFFFF),
                    modifier = Modifier.weight(1F)
                )
                if (subTitle != null) {
                    Text(
                        text = subTitle,
                        fontSize = 14.sp,
                        lineHeight = 14.sp,
                        color = Color(0xFF7E848D),
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
                Icon(
                    Icons.Filled.KeyboardArrowRight,
                    contentDescription = "Go to",
                    tint = Color(0xFF7E848D),
                )
            }
        }
        HorizontalDivider(
            color = Color(0xFF232533),
        )
    }
}