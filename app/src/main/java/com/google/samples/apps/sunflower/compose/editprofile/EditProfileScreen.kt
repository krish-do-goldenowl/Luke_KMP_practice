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

package com.google.samples.apps.sunflower.compose.editprofile

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.samples.apps.sunflower.R
import com.google.samples.apps.sunflower.compose.utils.BankTopAppBar
import kotlinx.coroutines.launch

@Composable
fun EditProfileScreen(
    onBack: () -> Unit,
) {
    Scaffold(
        modifier = Modifier.safeDrawingPadding(),
        topBar = {
            BankTopAppBar(
                title = "Edit Profile",
                onBack = onBack,
            )
        }
    ) { contentPadding ->
        var fullName by remember {
            mutableStateOf(value = "Tanya Myroniuk")
        }
        var email by remember {
            mutableStateOf(value = "Tanya Myroniuk@gmail.com")
        }
        var phone by remember {
            mutableStateOf(value = "+8801712663389")
        }
        var dateExpanded by remember { mutableStateOf(false) }
        var monthExpanded by remember { mutableStateOf(false) }
        var yearExpanded by remember { mutableStateOf(false) }

        var date by remember { mutableStateOf("28") }
        var month by remember { mutableStateOf("September") }
        var year by remember { mutableStateOf("2000") }
        val listMonth = listOf(
            "January",
            "February",
            "March",
            "April",
            "May",
            "June",
            "July",
            "August",
            "September",
            "October",
            "November",
            "December",
        )

        var dateScrollState = rememberScrollState(27)
        var monthScrollState = rememberScrollState(8)
        var yearScrollState = rememberScrollState(26)

        val coroutineScope = rememberCoroutineScope()

        Column(
            modifier = Modifier
                .padding(20.dp, contentPadding.calculateTopPadding(), 20.dp, 0.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(32.dp))
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
            Spacer(modifier = Modifier.height(21.dp))
            Text(
                text = fullName,
                fontSize = 17.sp,
                lineHeight = 17.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFFFFFFFF)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Senior Designer",
                fontSize = 12.sp,
                lineHeight = 12.sp,
                color = Color(0xFF7E848D)
            )
            Spacer(modifier = Modifier.height(30.dp))
            EditProfileField(
                label = "Full Name",
                iconSource = R.drawable.profile_user,
                value = fullName,
                onValueChange = {value ->
                    fullName = value
                },
            )
            Spacer(modifier = Modifier.height(21.dp))
            EditProfileField(
                label = "Email Address",
                iconSource = R.drawable.email,
                value = email,
                onValueChange = {value ->
                    email = value
                },
            )
            Spacer(modifier = Modifier.height(21.dp))
            EditProfileField(
                label = "Phone Number",
                iconSource = R.drawable.phone,
                value = phone,
                onValueChange = {value ->
                    phone = value
                },
                keyboardType = KeyboardType.Number,
            )
            Spacer(modifier = Modifier.height(21.dp))
            EditProfileTitle(title = "Birth Date")
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
                            val item = listMonth[i-1]
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = item,
                                        color = Color.White,
                                        textAlign = TextAlign.Center,
                                    )
                                },
                                onClick = {
                                    month = item
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
            Spacer(modifier = Modifier.weight(1F))
            Text(
                text = "Joined 28 Jan 2021",
                color = Color(0xFF7E848D),
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(50.dp))
        }
    }
}

@Composable
fun EditProfileField(
    label: String,
    iconSource: Int,
    value: String,
    onValueChange: (String) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Text,
) {
    Column {
        EditProfileTitle(label)
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
private fun EditProfileTitle(
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