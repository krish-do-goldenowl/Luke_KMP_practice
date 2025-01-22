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

package com.google.samples.apps.sunflower.compose.sendmoney

import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.google.samples.apps.sunflower.compose.requestmoney.MoneyCard
import com.google.samples.apps.sunflower.compose.utils.BankTopAppBar
import com.google.samples.apps.sunflower.ui.md_theme_light_primary

data class Receiver(
    val name: String,
    val imageSrc: Int,
)

val sampleReceivers = listOf(
    Receiver(
        name = "Yamilet",
        imageSrc = R.drawable.yamilet_ava
    ),
    Receiver(
        name = "Alexa",
        imageSrc = R.drawable.alexa_ava
    ),
    Receiver(
        name = "Yakub",
        imageSrc = R.drawable.yakub_ava
    ),
    Receiver(
        name = "Krishna",
        imageSrc = R.drawable.krishna_ava
    ),
)

val receivers = sampleReceivers + sampleReceivers + sampleReceivers

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SendMoneyScreen(
    onBack: () -> Unit,
    onSendMoney: () -> Unit,
) {
    Scaffold(
        modifier = Modifier.safeDrawingPadding(),
        topBar = {
            BankTopAppBar(
                title = "Send Money",
                onBack = onBack,
            )
        }
    ) { contentPadding ->
        val configuration = LocalConfiguration.current
        val screenWith = configuration.screenWidthDp
        var money by remember {
            mutableStateOf(value = "36.00")
        }
        val pagerState = rememberPagerState(
            initialPage = 2,
            pageCount = {5}
        )
        val listState = rememberLazyListState()

        Box {
            Column(
                modifier = Modifier.padding(top = contentPadding.calculateTopPadding())
            ) {
                Spacer(modifier = Modifier.height(31.dp))
                HorizontalPager(
                    state = pagerState,
//                    pageSize = PageSize.Fixed((screenWith - 20).dp),
                    contentPadding = PaddingValues(horizontal = 15.dp)
                ){
                    BankCard(PaddingValues(horizontal = 5.dp))
                }
                Spacer(modifier = Modifier.height(30.dp))
                Column(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Card(
//                        onClick = { /*TODO*/ },
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFF1E1E2D),
                        ),
                        elevation = CardDefaults.elevatedCardElevation(
                            defaultElevation = 1.dp,
                        ),
                    ){
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp, 15.dp, 0.dp, 15.dp)
                        ) {
                            Text(
                                text = "Send to",
                                fontSize = 14.sp,
                                lineHeight = 14.sp,
                                color = Color.White,
                            )
                            Spacer(modifier = Modifier.height(15.dp))
                            Row {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Box(
                                        contentAlignment = Alignment.Center,
                                        modifier = Modifier
                                            .border(
                                                width = 1.dp,
                                                color = md_theme_light_primary,
                                                shape = CircleShape
                                            )
                                            .width(48.dp)
                                            .height(48.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Outlined.Add,
                                            contentDescription = "Add Receiver",
                                            tint = md_theme_light_primary
                                        )
                                    }
                                    Spacer(modifier = Modifier.height(7.dp))
                                    Text(
                                        text = "Add",
                                        color = Color.White,
                                        fontSize = 11.sp,
                                        lineHeight = 11.sp
                                    )
                                }
                                Spacer(modifier = Modifier.width(18.dp))
                                LazyRow(
                                    state = listState,
                                    modifier = Modifier.weight(1F),
                                    contentPadding = PaddingValues(0.dp)
                                ) {
                                    items(receivers.size){ index ->
                                        val item = receivers[index]
                                        ReceiverItem(item)
                                    }
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(31.dp))
                    MoneyCard(
                        value = money,
                        onValueChange = {
                            money = it
                        }
                    )
                    Spacer(modifier = Modifier.weight(1F))
                    Button(
                        onClick = onSendMoney,
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                    ) {
                        Text(
                            text = "Send Money",
                            fontSize = 16.sp,
                            fontWeight = FontWeight(600),
                            lineHeight = 24.sp,
                        )
                    }
                    Spacer(modifier = Modifier.height(50.dp))
                }
            }
            Image(
                painter = painterResource(id = R.drawable.card_top_stack_2),
                contentDescription = "Card Top Stack",
                modifier = Modifier
                    .width((screenWith * 270 / 375).dp)
                    .height((screenWith * 361 / 375).dp)
                    .offset((screenWith / 2.6).dp, (screenWith / 2.6).dp)
            )
            Image(
                painter = painterResource(id = R.drawable.card_top_stack_3),
                contentDescription = "Card Top Stack",
                modifier = Modifier
                    .width((screenWith * 270 / 375).dp)
                    .height((screenWith * 361 / 375).dp)
                    .offset((-screenWith / 5.2).dp, (screenWith / 2.6).dp)
            )
        }
    }
}

@Composable
private fun ReceiverItem(item: Receiver) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(end = 18.dp)
    ) {
        Image(
            painter = painterResource(id = item.imageSrc),
            contentDescription = "Receiver Item ${item.name}",
            modifier = Modifier
                .width(48.dp)
                .height(48.dp),
        )
        Spacer(modifier = Modifier.height(7.dp))
        Text(
            text = item.name,
            color = Color.White,
            fontSize = 11.sp,
            lineHeight = 11.sp
        )
    }
}
