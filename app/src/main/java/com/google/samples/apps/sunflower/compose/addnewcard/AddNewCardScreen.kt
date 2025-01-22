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

package com.google.samples.apps.sunflower.compose.addnewcard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.samples.apps.sunflower.R
import com.google.samples.apps.sunflower.compose.homebank.BankCard
import com.google.samples.apps.sunflower.compose.utils.BankTopAppBar


@Composable
fun AddNewCardScreen(
    onBack: () -> Unit = {},
) {
    val configuration = LocalConfiguration.current
    val screenWith = configuration.screenWidthDp
    var cardHolderName by remember {
        mutableStateOf(value = "Tanya Myroniuk")
    }
    var expiryDate by remember {
        mutableStateOf(value = "09/06/2024")
    }
    var cvv by remember {
        mutableStateOf(value = "6986")
    }
    var cardNumber by remember {
        mutableStateOf(value = "4562 1122 4595 7852")
    }

    Scaffold(
        modifier = Modifier.safeDrawingPadding(),
        topBar = {
            BankTopAppBar(
                title = "Add New Card",
                onBack = onBack,
            )
        }
    ) {contentPadding ->
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .padding(20.dp, contentPadding.calculateTopPadding(), 20.dp, 0.dp)
                    .fillMaxSize(),
            ) {
                Spacer(modifier = Modifier.height(32.dp))
                BankCard()
                Spacer(modifier = Modifier.height(29.dp))
                FieldTitle("Cardholder Name")
                CardField(
                    value = cardHolderName,
                    onValueChange = {
                        cardHolderName = it
                    },
                    leadingIcon = {
                        Image(
                            painter = painterResource(id = R.drawable.profile_user),
                            contentDescription = "Cardholder Name",
                            modifier = Modifier
                                .width(22.dp)
                                .height(22.dp)
                        )
                    }
                )
                Spacer(modifier = Modifier.height(21.dp))
                Row {
                    Column(
                        modifier = Modifier.weight(3F)
                    ) {
                        FieldTitle("Expiry Date")
                        CardField(
                            value = expiryDate,
                            onValueChange = {
                                expiryDate = it
                            }
                        )
                    }
                    Spacer(modifier = Modifier.weight(2F))
                    Column(
                        modifier = Modifier.weight(3F)
                    ) {
                        FieldTitle("4-digit CVV")
                        CardField(
                            value = cvv,
                            onValueChange = {
                                cvv = it
                            }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(21.dp))
                FieldTitle("Card Number")
                CardField(
                    value = cardNumber,
                    onValueChange = {
                        cardNumber = it
                    },
                    leadingIcon = {
                        Image(
                            painter = painterResource(id = R.drawable.profile_payment),
                            contentDescription = "Card Number Leading",
                            modifier = Modifier
                                .width(22.dp)
                                .height(22.dp)
                        )
                    },
                    trailingIcon = {
                        Image(
                            painter = painterResource(id = R.drawable.mastercard),
                            contentDescription = "Card Number Trailing",
                            modifier = Modifier
                                .width(30.dp)
                                .height(18.dp)
                        )
                    }
                )
            }
            Image(
                painter = painterResource(id = R.drawable.card_top_stack_2),
                contentDescription = "Card Top Stack",
                modifier = Modifier
                    .width((screenWith * 270 / 375).dp)
                    .height((screenWith * 361 / 375).dp)
                    .offset((screenWith / 2.6).dp, (screenWith / 2.6).dp)
            )
        }
    }
}

@Composable
private fun FieldTitle(
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

@Composable
private fun CardField(
    value: String,
    onValueChange: (String) -> Unit,
    leadingIcon: @Composable() (() -> Unit)? = null,
    trailingIcon: @Composable() (() -> Unit)? = null
) {
    TextField(
        value = value,
        textStyle = TextStyle(
            fontSize = 14.sp,
            lineHeight = 14.sp,
            color = Color.White,
        ),
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        onValueChange = onValueChange,
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