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

package com.google.samples.apps.sunflower.compose.homebank

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.samples.apps.sunflower.R

@Composable
fun BankCard(
    paddingValues: PaddingValues = PaddingValues(0.dp)
) {
    val configuration = LocalConfiguration.current
    val screenWith = configuration.screenWidthDp
    Card(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues)
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
        Box {
            Image(
                painter = painterResource(id = R.drawable.card_map),
                contentDescription = "Card Map",
                contentScale = ContentScale.FillHeight,
                alpha = 0.16F,
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = (screenWith * 22 / 375).dp,
                        horizontal = (screenWith * 20 / 375).dp
                    )
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.card_sim),
                        contentDescription = "Card Sim",
                        modifier = Modifier
                            .width((screenWith * 29 / 375).dp)
                            .height((screenWith * 25 / 375).dp),
                    )
                    Image(
                        painter = painterResource(id = R.drawable.card_wifi),
                        contentDescription = "Card Wifi",
                        modifier = Modifier
                            .width((screenWith * 16 / 375).dp)
                            .height((screenWith * 21 / 375).dp),
                    )
                }
                Spacer(modifier = Modifier.height((screenWith * 26 / 375).dp))
                Text(
                    text = "4562   1122   4595   7852",
                    fontSize = (screenWith * 24 / 375).sp,
                    lineHeight = (screenWith * 29 / 375).sp,
                    color = Color(0xFFFFFFFF),
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height((screenWith * 12 / 375).dp))
                Text(
                    text = "AR Jonson",
                    fontSize = (screenWith * 13 / 375).sp,
                    lineHeight = (screenWith * 15.73 / 375).sp,
                    color = Color(0xFFFFFFFF),
                )
                Spacer(modifier = Modifier.weight(1F))
                Row(
                    verticalAlignment = Alignment.Bottom
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
                    Spacer(modifier = Modifier.width((screenWith * 22 / 375).dp))
                    Column {
                        Text(
                            text = "CVV",
                            fontSize = (screenWith * 9 / 375).sp,
                            lineHeight = (screenWith * 10.89 / 375).sp,
                            color = Color(0xFFA2A2A7),
                        )
                        Text(
                            text = "6986",
                            fontSize = (screenWith * 13 / 375).sp,
                            lineHeight = (screenWith * 15.73 / 375).sp,
                            color = Color(0xFFFFFFFF),
                        )
                    }
                    Spacer(modifier = Modifier.weight(1F))
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.mastercard),
                            contentDescription = "Mastercard",
                            modifier = Modifier
                                .width((screenWith * 36 / 375).dp)
                                .height((screenWith * 20 / 375).dp)
                        )
                        Text(
                            text = "Mastercard",
                            fontSize = (screenWith * 13 / 375).sp,
                            lineHeight = (screenWith * 15.73 / 375).sp,
                            color = Color(0xFFFFFFFF),
                        )
                    }
                }
            }
        }
    }
}