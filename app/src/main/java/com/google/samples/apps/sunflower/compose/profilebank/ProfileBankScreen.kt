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

package com.google.samples.apps.sunflower.compose.profilebank

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.samples.apps.sunflower.R
import com.google.samples.apps.sunflower.compose.utils.BankTopAppBar

@Composable
fun ProfileBankScreen(
    onBack: () -> Unit,
    onEditProfile: () -> Unit,
    onBanksAndCards: () -> Unit,
    onRequestMoney: () -> Unit,
) {
    Scaffold(
        modifier = Modifier.safeDrawingPadding(),
        topBar = {
            BankTopAppBar(
                title = "Profile",
                iconSource = R.drawable.profile_edit_icon,
                onBack = onBack,
                onActionButton = onEditProfile,
            )
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(20.dp, contentPadding.calculateTopPadding(), 20.dp, 0.dp)
                .fillMaxSize(),
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            ProfileAvatarAndName()
            Spacer(modifier = Modifier.height(32.dp))
            ProfileButton(
                title = "Personal Information",
                iconSource = R.drawable.profile_user,
            )
            Spacer(modifier = Modifier.height(22.dp))
            ProfileButton(
                title = "Payment Preferences",
                iconSource = R.drawable.profile_payment,
                onClick = onRequestMoney,
            )
            Spacer(modifier = Modifier.height(22.dp))
            ProfileButton(
                title = "Banks and Cards",
                iconSource = R.drawable.profile_edit_card,
                onClick = onBanksAndCards,
            )
            Spacer(modifier = Modifier.height(22.dp))
            ProfileButton(
                title = "Notifications",
                iconSource = R.drawable.profile_message,
                trailingIcon = {
                    Text(
                        text = "2",
                        color = Color.White,
                        fontSize = 11.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .width(22.dp)
                            .height(22.dp)
                            .background(
                                color = Color.Red,
                                shape = CircleShape,
                            )
                    )
                }
            )
            Spacer(modifier = Modifier.height(22.dp))
            ProfileButton(
                title = "Message Center",
                iconSource = R.drawable.profile_message,
            )
            Spacer(modifier = Modifier.height(22.dp))
            ProfileButton(
                title = "Address",
                iconSource = R.drawable.profile_address,
            )
            Spacer(modifier = Modifier.height(22.dp))
            ProfileButton(
                title = "Settings",
                iconSource = R.drawable.profile_setting,
            )
        }
    }
}

@Composable
private fun ProfileAvatarAndName() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.user_avatar),
            contentDescription = "Avatar",
            modifier = Modifier
                .width(70.dp)
                .height(70.dp)
                .border(
                    width = 0.dp,
                    color = Color.Transparent,
                    shape = CircleShape,
                )
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "Tanya Myroniuk",
                fontSize = 17.sp,
                lineHeight = 17.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFFFFFFFF)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Senior Designer",
                fontSize = 12.sp,
                lineHeight = 12.sp,
                color = Color(0xFF7E848D)
            )
        }
    }
}

@Composable
private fun ProfileButton(
    title: String,
    iconSource: Int,
    trailingIcon: @Composable () -> Unit = {
        Icon(
            Icons.Filled.KeyboardArrowRight,
            contentDescription = "Go to",
            tint = Color(0xFF7E848D),
        )
    },
    onClick: () -> Unit = {}
) {
    Column {
        TextButton(
            onClick = onClick,
            contentPadding = PaddingValues(0.dp),
        ){
            Row {
                Image(
                    painter = painterResource(id = iconSource),
                    contentDescription = "$title Profile Button $iconSource",
                    modifier = Modifier
                        .width(22.dp),
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = title,
                    fontSize = 14.sp,
                    lineHeight = 14.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFFFFFFFF),
                    modifier = Modifier.weight(1F)
                )
                trailingIcon()
            }
        }
        HorizontalDivider(
            color = Color(0xFF232533),
        )
    }
}