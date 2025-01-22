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

package com.google.samples.apps.sunflower.compose.changepassword

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.samples.apps.sunflower.R
import com.google.samples.apps.sunflower.compose.utils.BankTopAppBar

@Composable
fun ChangePasswordScreen(
    onBack: () -> Unit = {},
) {
    Scaffold(
        modifier = Modifier.safeDrawingPadding(),
        topBar = {
            BankTopAppBar(
                title = "Change Password",
                onBack = onBack,
            )
        }
    ) {it ->
        var currentPassword by remember {
            mutableStateOf(value = "")
        }
        var newPassword by remember {
            mutableStateOf(value = "")
        }
        var confirmPassword by remember {
            mutableStateOf(value = "")
        }
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(it)
                .padding(20.dp),
        ) {
            Spacer(modifier = Modifier.height(31.dp))
            ChangePasswordTitle("Current Password")
            ChangePasswordField(
                value = currentPassword,
                onValueChange = {
                    currentPassword = it
                }
            )
            Spacer(modifier = Modifier.height(21.dp))
            ChangePasswordTitle("New Password")
            ChangePasswordField(
                value = newPassword,
                onValueChange = {
                    newPassword = it
                }
            )
            Spacer(modifier = Modifier.height(21.dp))
            ChangePasswordTitle("Confirm Password")
            ChangePasswordField(
                value = confirmPassword,
                onValueChange = {
                    confirmPassword = it
                }
            )
            Spacer(modifier = Modifier.height(9.dp))
            Text(
                text = "Both Passwords Must Match",
                fontSize = 12.sp,
                lineHeight = 12.sp,
                color = Color(0xFFA2A2A7)
            )
            Spacer(modifier = Modifier.height(39.dp))
            Button(
                onClick = {

                },
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text(
                    text = "Change Password",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(600),
                    lineHeight = 24.sp,
                )
            }
        }
    }
}

@Composable
private fun ChangePasswordField(
    value: String,
    onValueChange: (String) -> Unit,
) {
    var passwordVisibility by remember {
        mutableStateOf(value = false)
    }
    TextField(
        value = value,
        textStyle = TextStyle(
            fontSize = 14.sp,
            lineHeight = 14.sp
        ),
        leadingIcon = {
            Image(
                painter = painterResource(id = R.drawable.password),
                contentDescription = "Password",
            )
        },
        trailingIcon = {
            IconButton(
                onClick = {
                    passwordVisibility = !passwordVisibility
                },
            ) {
                Image(
                    painter = painterResource(id = if(passwordVisibility) R.drawable.hide_password else R.drawable.show_password),
                    contentDescription = "Show Password",
                )
            }
        },
        onValueChange = onValueChange,
        visualTransformation = if(passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
        modifier = Modifier
            .fillMaxWidth(),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            errorContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            focusedTextColor = Color(0xFFFFFFFF),
            unfocusedTextColor = Color(0xFFFFFFFF),
            disabledTextColor = Color.Gray,
            errorTextColor = Color.Red,
        ),
        maxLines = 1,
        minLines = 1,
    )
}

@Composable
private fun ChangePasswordTitle(
    title: String
) {
    Text(
        text = title,
        color = Color(0xFFA2A2A7),
        fontSize = 14.sp,
        lineHeight = 14.sp,
        textAlign = TextAlign.Start,
        modifier = Modifier.fillMaxWidth(),
    )
}
