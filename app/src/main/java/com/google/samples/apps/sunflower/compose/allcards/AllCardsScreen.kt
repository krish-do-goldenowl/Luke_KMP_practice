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

package com.google.samples.apps.sunflower.compose.allcards

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.samples.apps.sunflower.R
import com.google.samples.apps.sunflower.compose.homebank.BankCard
import com.google.samples.apps.sunflower.compose.utils.BankTopAppBar

@Composable
fun AllCardsScreen(
    onBack: () -> Unit = {},
    onAddCard: () -> Unit,
) {
    val configuration = LocalConfiguration.current
    val screenWith = configuration.screenWidthDp

    Scaffold(
        modifier = Modifier.safeDrawingPadding(),
        topBar = {
            BankTopAppBar(
                title = "All Cards",
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
                Spacer(modifier = Modifier.height(24.dp))
                Card(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1.6F),
                    shape = RoundedCornerShape(25.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF25253D),
                    ),
                    elevation = CardDefaults.elevatedCardElevation(
                        defaultElevation = 5.dp,
                    ),
                    border = BorderStroke(1.dp, Color(0xFF2A2C3C)),
                ) {
                    Column(
                        horizontalAlignment = Alignment.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                vertical = (screenWith * 22 / 375).dp,
                                horizontal = (screenWith * 20 / 375).dp
                            )
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.card_chip),
                            contentDescription = "Card Sim",
                            modifier = Modifier
                                .width((screenWith * 35 / 375).dp)
                                .height((screenWith * 25 / 375).dp),
                        )
                        Spacer(modifier = Modifier.height((screenWith * 26 / 375).dp))
                        Text(
                            text = "4562   1122   4595   7852",
                            fontSize = (screenWith * 24 / 375).sp,
                            lineHeight = (screenWith * 29 / 375).sp,
                            color = Color(0xFFFFFFFF),
                        )
                        Spacer(modifier = Modifier.height((screenWith * 12 / 375).dp))
                        Text(
                            text = "Smith Jonson",
                            fontSize = (screenWith * 13 / 375).sp,
                            lineHeight = (screenWith * 15.73 / 375).sp,
                            color = Color(0xFFFFFFFF),
                        )
                        Spacer(modifier = Modifier.weight(1F))
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.Bottom,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column {
                                Text(
                                    text = "Expiry Date",
                                    fontSize = (screenWith * 9 / 375).sp,
                                    lineHeight = (screenWith * 10.89 / 375).sp,
                                    color = Color(0xFFA2A2A7),
                                )
                                Text(
                                    text = "24/2000",
                                    fontSize = (screenWith * 13 / 375).sp,
                                    lineHeight = (screenWith * 15.73 / 375).sp,
                                    color = Color(0xFFFFFFFF),
                                )
                            }
                            Image(
                                painter = painterResource(id = R.drawable.visa),
                                contentDescription = "Mastercard",
                                modifier = Modifier
                                    .width((screenWith * 44 / 375).dp)
                                    .height((screenWith * 13 / 375).dp)
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.weight(1F))
                Button(
                    onClick = onAddCard,
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = "Add Card",
                            fontSize = 16.sp,
                            fontWeight = FontWeight(600),
                            lineHeight = 24.sp,
                        )
                        Spacer(modifier = Modifier.width(3.dp))
                        Icon(
                            Icons.Outlined.Add,
                            contentDescription = "Add Card",
                        )
                    }
                }
                Spacer(modifier = Modifier.height(50.dp))
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