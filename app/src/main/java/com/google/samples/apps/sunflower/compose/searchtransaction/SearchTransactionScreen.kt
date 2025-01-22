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

package com.google.samples.apps.sunflower.compose.searchtransaction

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.samples.apps.sunflower.R
import com.google.samples.apps.sunflower.compose.homebank.TransactionItem
import com.google.samples.apps.sunflower.compose.homebank.transactions
import com.google.samples.apps.sunflower.compose.utils.BankTopAppBar
import okhttp3.internal.filterList


@Composable
fun SearchTransactionScreen(
    onBack: () -> Unit,
) {
    val listState = rememberLazyListState()
    var searchText by remember {
        mutableStateOf(value = "")
    }
    val listTransactions = transactions.filterList {
        this.name.lowercase().contains(searchText.lowercase())
    }

    Scaffold(
        modifier = Modifier.safeDrawingPadding(),
        topBar = {
            BankTopAppBar(
                title = "Search",
                onBack = onBack,
                iconSource = R.drawable.cancel,
                onActionButton = onBack
            )
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(20.dp, contentPadding.calculateTopPadding(), 20.dp, 0.dp)
                .fillMaxSize(),
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = searchText,
                onValueChange = {
                    searchText = it
                },
                placeholder = {
                    Text(
                        text = "Search",
                        color = Color(0xFFBBBBC1),
                        fontSize = 14.sp,
                        lineHeight = 14.sp
                    )
                },
                leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.search),
                        contentDescription = "Search Transaction",
                        colorFilter = ColorFilter.tint(color = Color(0xFFBBBBC1))
                    )
                },
                trailingIcon = {
                    if (searchText.isNotEmpty()){
                        Button(
                            onClick = {
                                searchText = ""
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Transparent,
                            ),
                            shape = CircleShape,
                            contentPadding = PaddingValues(0.dp),
                            elevation = ButtonDefaults.buttonElevation(
                                defaultElevation = 0.dp,
                                pressedElevation = 0.dp,
                                focusedElevation = 0.dp,
                                hoveredElevation = 0.dp,
                                disabledElevation = 0.dp,
                            )
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.cancel),
                                contentDescription = "Cancel Search Transaction",
                                colorFilter = ColorFilter.tint(color = Color(0xFFBBBBC1))
                            )
                        }
                    }
                },
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFF232533),
                    unfocusedContainerColor = Color(0xFF232533),
                    errorContainerColor = Color(0xFF232533),
                    disabledContainerColor = Color(0xFF232533),
                    focusedTextColor = Color(0xFFFFFFFF),
                    unfocusedTextColor = Color(0xFFFFFFFF),
                    disabledTextColor = Color.Gray,
                    errorTextColor = Color.Red,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                maxLines = 1,
                minLines = 1,
            )
            Spacer(modifier = Modifier.height(19.dp))
            LazyColumn(
                state = listState,
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(0.dp)
            ) {
                items(listTransactions.size){ index ->
                    val item = listTransactions[index]
                    TransactionItem(item)
                }
            }
        }
    }
}