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

package com.google.samples.apps.sunflower.compose.mycards

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.samples.apps.sunflower.R
import com.google.samples.apps.sunflower.compose.homebank.BankCard
import com.google.samples.apps.sunflower.compose.homebank.TransactionItem
import com.google.samples.apps.sunflower.compose.homebank.transactions
import com.google.samples.apps.sunflower.compose.utils.BankTopAppBar
import com.google.samples.apps.sunflower.ui.md_theme_light_primary

@Composable
fun MyCardsScreen(
    onAddNewCard: () -> Unit
) {
    val configuration = LocalConfiguration.current
    val screenWith = configuration.screenWidthDp
    val listState = rememberLazyListState()
    Scaffold(
        topBar = {
            BankTopAppBar(
                title = "My Cards",
                iconSource = R.drawable.add_icon,
                hasBackIcon = false,
                onActionButton = onAddNewCard
            )
        }
    ) { contentPadding ->
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
                Spacer(modifier = Modifier.height(30.dp))
                LazyColumn(
                    state = listState,
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1F),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    items(transactions.size){ index ->
                        val item = transactions[index]
                        TransactionItem(item)
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Monthly spending limit",
                    fontSize = 18.sp,
                    lineHeight = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFFFFFFFF)
                )
                Spacer(modifier = Modifier.height(19.dp))
                Card(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF1E1E2D),
                    ),
                    elevation = CardDefaults.elevatedCardElevation(
                        defaultElevation = 5.dp,
                    ),
                    border = BorderStroke(1.dp, Color(0xFF232533)),
                ) {
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .padding(23.dp),
                    ) {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            Text(
                                text = "Amount: $8,545.00",
                                fontSize = 13.sp,
                                lineHeight = 13.sp,
                                color = Color(0xFFFFFFFF),
                            )
                            Spacer(modifier = Modifier.height(11.dp))
                            Box(
                                contentAlignment = Alignment.Center
                            ) {
                                Row(
                                    modifier = Modifier
                                        .height(7.dp)
                                        .fillMaxWidth()
                                        .border(
                                            width = 0.dp,
                                            color = Color.Transparent,
                                            shape = RoundedCornerShape(7.dp),
                                        )
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxHeight()
                                            .weight(46F)
                                            .background(
                                                color = Color(0xFFFFFFFF),
                                                shape = RoundedCornerShape(7.dp),
                                            )
                                            .border(
                                                width = 0.dp,
                                                color = Color.Transparent,
                                                shape = RoundedCornerShape(7.dp),
                                            )
                                    )
                                    Box(
                                        modifier = Modifier
                                            .fillMaxHeight()
                                            .weight(54F)
                                            .background(
                                                color = Color(0xFF292937),
                                                shape = RoundedCornerShape(7.dp),
                                            )
                                            .border(
                                                width = 0.dp,
                                                color = Color.Transparent,
                                                shape = RoundedCornerShape(7.dp),
                                            )
                                    )
                                }
                                Row {
                                    Spacer(modifier = Modifier.weight(46F))
                                    Box(
                                        modifier = Modifier
                                            .height(16.dp)
                                            .width(16.dp)
                                            .border(
                                                width = 3.5.dp,
                                                color = md_theme_light_primary,
                                                shape = CircleShape,
                                            )
                                            .background(
                                                color = Color(0xFFFFFFFF),
                                                shape = CircleShape,
                                            )
                                    )
                                    Spacer(modifier = Modifier.weight(54F))
                                }
                            }
                            Spacer(modifier = Modifier.height(11.dp))
                            Row {
                                Text(
                                    text = "$0",
                                    fontSize = 12.sp,
                                    lineHeight = 12.sp,
                                    color = Color(0xFFA2A2A7),
                                    modifier = Modifier.weight(0.46F),
                                )
                                Text(
                                    text = "$4,600",
                                    fontSize = 12.sp,
                                    lineHeight = 12.sp,
                                    color = Color(0xFFFFFFFF),
                                )
                                Text(
                                    text = "$10,000",
                                    fontSize = 12.sp,
                                    lineHeight = 12.sp,
                                    color = Color(0xFFA2A2A7),
                                    textAlign = TextAlign.End,
                                    modifier = Modifier.weight(0.54F),
                                )
                            }
                            Spacer(modifier = Modifier.height(11.dp))
                        }
                    }
                }
                Spacer(modifier = Modifier.height(18.dp))
            }
            Image(
                painter = painterResource(id = R.drawable.card_top_stack),
                contentDescription = "Card Top Stack",
                modifier = Modifier
                    .width((screenWith * 270 / 375).dp)
                    .height((screenWith * 361 / 375).dp)
                    .offset((screenWith / 2.6).dp, (screenWith / 2.6).dp)
            )
        }
    }
}

