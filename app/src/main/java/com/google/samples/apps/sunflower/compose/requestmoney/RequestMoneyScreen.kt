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

package com.google.samples.apps.sunflower.compose.requestmoney

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.samples.apps.sunflower.R
import com.google.samples.apps.sunflower.compose.utils.BankTopAppBar
import kotlinx.coroutines.launch


@Composable
fun RequestMoneyScreen(
    onBack: () -> Unit,
) {
    Scaffold(
        modifier = Modifier.safeDrawingPadding(),
        topBar = {
            BankTopAppBar(
                title = "Request Money",
                onBack = onBack,
            )
        }
    ) { contentPadding ->
        var payerName by remember {
            mutableStateOf(value = "Tanya Myroniuk")
        }
        var email by remember {
            mutableStateOf(value = "Tanya Myroniuk@gmail.com")
        }
        var desc by remember {
            mutableStateOf(value = "Tanya Myroniuk")
        }
        var money by remember {
            mutableStateOf(value = "26.00.00")
        }
        var dateExpanded by remember { mutableStateOf(false) }
        var monthExpanded by remember { mutableStateOf(false) }
        var yearExpanded by remember { mutableStateOf(false) }

        var date by remember { mutableStateOf("28") }
        var month by remember { mutableStateOf("09") }
        var year by remember { mutableStateOf("2000") }

        val dateScrollState = rememberScrollState(27)
        val monthScrollState = rememberScrollState(8)
        val yearScrollState = rememberScrollState(26)

        val coroutineScope = rememberCoroutineScope()

        Column(
            modifier = Modifier
                .padding(20.dp, contentPadding.calculateTopPadding(), 20.dp, 0.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(31.dp))
            RequestMoneyField(
                label = "Payer Name",
                iconSource = R.drawable.profile_user,
                value = payerName,
                onValueChange = {value ->
                    payerName = value
                },
            )
            Spacer(modifier = Modifier.height(21.dp))
            RequestMoneyField(
                label = "Email Address",
                iconSource = R.drawable.email,
                value = email,
                onValueChange = {value ->
                    email = value
                },
            )
            Spacer(modifier = Modifier.height(21.dp))
            RequestMoneyField(
                label = "Description",
                iconSource = R.drawable.phone,
                value = desc,
                onValueChange = {value ->
                    desc = value
                },
            )
            Spacer(modifier = Modifier.height(21.dp))
            RequestMoneyTitle(title = "Birth Date")
            Row {
                Box(
                    modifier = Modifier.weight(1F),
                    contentAlignment = Alignment.Center,
                ) {
                    TextButton(
                        onClick = {
                            dateExpanded = !dateExpanded
                        },
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = date,
                                color = Color.White,
                                textAlign = TextAlign.Center,
                            )
                            Spacer(modifier = Modifier.height(7.dp))
                            HorizontalDivider(
                                color = Color(0xFF232533)
                            )
                        }
                    }
                    DropdownMenu(
                        expanded = dateExpanded,
                        onDismissRequest = { dateExpanded = false },
                        scrollState = dateScrollState,
                        modifier = Modifier.height(300.dp)
                    ) {
                        for (i in 1..31) {
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = "$i",
                                        color = Color.White,
                                        textAlign = TextAlign.Center,
                                    )
                                },
                                onClick = {
                                    date = i.toString()
                                    dateExpanded = false
                                    coroutineScope.launch {
                                        dateScrollState.scrollTo(1-i)
                                        dateScrollState.scrollTo(1-i)
                                    }
                                },
                                contentPadding = PaddingValues(10.dp)
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.width(43.dp))
                Box(
                    modifier = Modifier.weight(1F),
                    contentAlignment = Alignment.Center,
                ) {
                    TextButton(
                        onClick = {
                            monthExpanded = !monthExpanded
                        },
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = month,
                                color = Color.White,
                                textAlign = TextAlign.Center,
                            )
                            Spacer(modifier = Modifier.height(7.dp))
                            HorizontalDivider(
                                color = Color(0xFF232533)
                            )
                        }
                    }
                    DropdownMenu(
                        expanded = monthExpanded,
                        onDismissRequest = { monthExpanded = false },
                        modifier = Modifier.height(300.dp),
                        scrollState = monthScrollState,
                    ) {
                        for (i in 1..12) {
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text =  "${if(i.toString().length == 1) "0" else ""}${i}",
                                        color = Color.White,
                                        textAlign = TextAlign.Center,
                                    )
                                },
                                onClick = {
                                    month = "${if(i.toString().length == 1) "0" else ""}${i}"
                                    monthExpanded = false
                                    coroutineScope.launch {
                                        monthScrollState.scrollTo(1-i)
                                    }
                                },
                                contentPadding = PaddingValues(10.dp)
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.width(43.dp))
                Box(
                    modifier = Modifier.weight(1F),
                    contentAlignment = Alignment.Center,
                ) {
                    TextButton(
                        onClick = {
                            yearExpanded = !yearExpanded
                        },
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = year,
                                color = Color.White,
                                textAlign = TextAlign.Center,
                            )
                            Spacer(modifier = Modifier.height(7.dp))
                            HorizontalDivider(
                                color = Color(0xFF232533)
                            )
                        }
                    }
                    DropdownMenu(
                        expanded = yearExpanded,
                        onDismissRequest = { yearExpanded = false },
                        modifier = Modifier.height(300.dp),
                        scrollState = yearScrollState,
                    ) {
                        for (i in 1975..2025) {
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = "$i",
                                        color = Color.White,
                                        textAlign = TextAlign.Center,
                                    )
                                },
                                onClick = {
                                    year = i.toString()
                                    yearExpanded = false
                                    coroutineScope.launch {
                                        yearScrollState.scrollTo(1975-i)
                                    }
                                },
                                contentPadding = PaddingValues(10.dp),
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
            MoneyCard(
                value = money,
                onValueChange = {
                    money = it
                }
            )
            Spacer(modifier = Modifier.weight(1F))
            Button(
                onClick = {

                },
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text(
                    text = "Request Money",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(600),
                    lineHeight = 24.sp,
                )
            }
            Spacer(modifier = Modifier.height(50.dp))
        }
    }
}

@Composable
private fun RequestMoneyField(
    label: String,
    iconSource: Int,
    value: String,
    onValueChange: (String) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Text,
) {
    Column {
        RequestMoneyTitle(label)
        TextField(
            value = value,
            onValueChange = onValueChange,
            leadingIcon = {
                Image(
                    painter = painterResource(id = iconSource),
                    contentDescription = "Password",
                    modifier = Modifier
                        .width(22.dp)
                        .height(22.dp)
                        .align(Alignment.Start)
                )
            },
            modifier = Modifier.fillMaxWidth(),
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
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType
            )
        )
    }
}

@Composable
private fun RequestMoneyTitle(
    title: String,
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
fun MoneyCard(
    value: String,
    onValueChange: (String) -> Unit
) {
    Card(
//        onClick = { /*TODO*/ },
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF1E1E2D),
        ),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 1.dp,
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 29.dp, 16.dp, 10.dp,)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Enter Your Amount",
                    fontSize = 11.sp,
                    lineHeight = 11.sp,
                    color = Color(0xFF9CB1D1)
                )
                Text(
                    text = "Change Currency?",
                    fontSize = 11.sp,
                    lineHeight = 11.sp,
                    color = Color(0xFFFF3F60)
                )
            }
//            Spacer(modifier = Modifier.height(19.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "USD",
                    fontSize = 24.sp,
                    lineHeight = 24.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFF9CB1D1)
                )
//                Spacer(modifier = Modifier.width(16.dp))
                TextField(
                    value = value,
                    onValueChange = onValueChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1F),
                    textStyle = TextStyle(
                        fontSize = 24.sp,
                        lineHeight = 24.sp,
                        fontWeight = FontWeight(600),
                        color = Color.White,
                    ),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        errorContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        focusedTextColor = Color(0xFFFFFFFF),
                        unfocusedTextColor = Color(0xFFFFFFFF),
                        disabledTextColor = Color.Gray,
                        errorTextColor = Color.Red,
                        disabledIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        errorIndicatorColor = Color.Transparent,
                    ),
                    maxLines = 1,
                    minLines = 1,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    )
                )
            }
        }
    }
}