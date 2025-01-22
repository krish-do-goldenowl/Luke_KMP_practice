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

package com.google.samples.apps.sunflower.compose.signinbank

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.samples.apps.sunflower.R
import com.google.samples.apps.sunflower.ui.SunflowerTheme
import com.google.samples.apps.sunflower.ui.md_theme_light_primary
import com.google.samples.apps.sunflower.viewmodels.UserViewModel

@Composable
fun SignInBankScreen(
    modifier: Modifier = Modifier,
    viewModel: UserViewModel = hiltViewModel(),
    onLoginClick: (String, String) -> Unit,
    onSignUpClick: () -> Unit,
) {
    SignInBankScreen(
        modifier = modifier,
        onLoginClick = onLoginClick,
        onSignUpClick = onSignUpClick,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SignInBankScreen(
    modifier: Modifier = Modifier,
    onLoginClick: (String, String) -> Unit = {_,_ ->},
    onSignUpClick: () -> Unit = {},
) {
    Scaffold(
        modifier
            .imePadding(),
    ) {it ->
        var email by remember {
            mutableStateOf(value = "")
        }
        var password by remember {
            mutableStateOf(value = "")
        }
        var passwordVisibility by remember {
            mutableStateOf(value = false)
        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxWidth()
                .padding(it)
                .padding(20.dp),
        ) {
            Spacer(modifier = Modifier.height(53.dp))
            Text(
                text = "Sign In",
                fontSize = 32.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFFFFFFFF),
                lineHeight = 32.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(38.dp))
            Text(
                text = "Email Address",
                color = Color(0xFFA2A2A7),
                fontSize = 14.sp,
                lineHeight = 14.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth(),
            )
            TextField(
                value = email,
                placeholder = {
                    Text(
                        text = "enter your email...",
                    )
                },
                leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.email),
                        contentDescription = "Email",
                        modifier = Modifier.align(Alignment.Start)
                    )
                },
                onValueChange = {
                    email = it
                },
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
            Spacer(modifier = Modifier.height(21.dp))
            Text(
                text = "Password",
                color = Color(0xFFA2A2A7),
                fontSize = 14.sp,
                lineHeight = 14.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth(),
            )
            TextField(
                value = password,
                placeholder = { Text(text = "enter your password...") },
                leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.password),
                        contentDescription = "Password",
                        modifier = Modifier.align(Alignment.Start)
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
                onValueChange = {
                    password = it
                },
                modifier = Modifier
                    .fillMaxWidth(),
                visualTransformation = if(passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
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
            Spacer(modifier = Modifier.height(40.dp))
            Button(
                onClick = {
                    onLoginClick(email, password)
                },
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text(
                    text = "Sign In",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(600),
                    lineHeight = 24.sp
                )
            }
            Spacer(modifier = Modifier.height(29.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "I'm a new user.",
                    color = Color(0xFFA2A2A7),
                    fontSize = 14.sp,
                    lineHeight = 14.sp,
                )
                TextButton(
                    onClick = onSignUpClick,
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Text(
                        text = "Sign Up",
                        color = md_theme_light_primary,
                        fontSize = 14.sp,
                        fontWeight = FontWeight(500),
                        lineHeight = 14.sp,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun SignInScreenPreview(
) {
    SunflowerTheme {
        SignInBankScreen()
    }
}

