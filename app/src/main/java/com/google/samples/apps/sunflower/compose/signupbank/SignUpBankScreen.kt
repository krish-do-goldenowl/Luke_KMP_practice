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

package com.google.samples.apps.sunflower.compose.signupbank

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
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
fun SignUpBankScreen(
    modifier: Modifier = Modifier,
    viewModel: UserViewModel = hiltViewModel(),
    onSignUpClick: (String, String, String, String) -> Unit,
    onBack: () -> Unit,
) {
    SignUpBankScreen(
        modifier = modifier,
        onSignUpClick = onSignUpClick,
        onBack = onBack,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SignUpBankScreen(
    modifier: Modifier = Modifier,
    onSignUpClick: (String, String, String, String) -> Unit = {_,_,_,_ ->},
    onBack: () -> Unit = {},
) {
    Scaffold(
        modifier
            .imePadding(),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                ),
                modifier = Modifier.padding(horizontal = 15.dp),
                navigationIcon = {
                    IconButton(
                        onClick = onBack,
                        modifier = Modifier.width(42.dp).height(42.dp)
                    ) {
                        Icon(
                            Icons.Filled.KeyboardArrowLeft,
                            contentDescription = stringResource(id = R.string.a11y_back),
                            tint = Color(0xFFFFFFFF),
                            modifier = Modifier.drawBehind {
                                drawCircle(
                                    color = Color(0xFF1E1E2D),
                                    radius = this.size.maxDimension
                                )
                            }
                        )
                    }
                },
                title = {}
            )
        }
    ) {it ->
        var name by remember {
            mutableStateOf(value = "")
        }
        var phone by remember {
            mutableStateOf(value = "")
        }
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
            horizontalAlignment = Alignment.Start,
            modifier = modifier
                .fillMaxWidth()
                .padding(it)
                .padding(20.dp),
        ) {

            Spacer(modifier = Modifier.height(53.dp))
            Text(
                text = "Sign Up",
                fontSize = 32.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFFFFFFFF),
                lineHeight = 32.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(38.dp))
            FieldTitle("Full Name")
            SignUpField(
                value = name,
                onValueChange = {
                    name = it
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Person,
                        contentDescription = "Full Name",
                        tint = Color(0xFFA2A2A7),
                    )
                },
                hintText = "enter your name...",
            )
            Spacer(modifier = Modifier.height(21.dp))
            FieldTitle("Phone Number")
            SignUpField(
                value = phone,
                onValueChange = {
                    phone = it
                },
                leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.phone),
                        contentDescription = "Phone",
                        modifier = Modifier.align(Alignment.Start)
                    )
                },
                hintText = "enter your phone number...",
                keyboardType = KeyboardType.Number,
            )
            Spacer(modifier = Modifier.height(21.dp))
            FieldTitle("Email Address")
            SignUpField(
                value = email,
                onValueChange = {
                    email = it
                },
                leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.email),
                        contentDescription = "Email",
                        modifier = Modifier.align(Alignment.Start)
                    )
                },
                hintText = "enter your email...",
            )
            Spacer(modifier = Modifier.height(21.dp))
            FieldTitle("Password")
            SignUpField(
                value = password,
                onValueChange = {
                    password = it
                },
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
                hintText = "enter your password...",
                visualTransformation = if(passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            )
            Spacer(modifier = Modifier.height(40.dp))
            Button(
                onClick = {
                    onSignUpClick(name, phone, email, password,)
                },
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text(
                    text = "Sign Up",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(600),
                    lineHeight = 24.sp
                )
            }
            Spacer(modifier = Modifier.height(29.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Already have an account.",
                    color = Color(0xFFA2A2A7),
                    fontSize = 14.sp,
                    lineHeight = 14.sp,
                )
                TextButton(
                    onClick = onBack,
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Text(
                        text = "Sign In",
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

@Composable
private fun FieldTitle(title: String) {
    Text(
        text = title,
        color = Color(0xFFA2A2A7),
        fontSize = 14.sp,
        lineHeight = 14.sp,
        textAlign = TextAlign.Start,
        modifier = Modifier.fillMaxWidth(),
    )
}

@Composable
private fun SignUpField(
    value: String,
    onValueChange: (String) -> Unit,
    leadingIcon: @Composable() (() -> Unit),
    trailingIcon: @Composable() (() -> Unit)? = null,
    hintText: String,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardType: KeyboardType = KeyboardType.Text,
) {
    TextField(
        value = value,
        textStyle = TextStyle(
            fontSize = 14.sp,
            lineHeight = 14.sp
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        placeholder = {
            Text(
                text = hintText,
            )
        },
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        onValueChange = onValueChange,
        visualTransformation = visualTransformation,
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

@Preview
@Composable
private fun SignUpScreenPreview(
) {
    SunflowerTheme {
        SignUpBankScreen()
    }
}

