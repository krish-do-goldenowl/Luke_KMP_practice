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

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.samples.apps.sunflower.R

data class Transaction(
    val name: String,
    val type: TransactionType,
    val productType: ProductType,
    val iconSource: Int,
    val price: Double
)

enum class TransactionType {
    SENT,
    RECEIVE,
}

enum class ProductType(
    val title: String
) {
    MUSIC(title = "Music"),
    SHOPPING(title = "Shopping"),
    ENTERTAINMENT(title = "Entertainment"),
    TRANSACTION(title = "Transaction"),
}

val sampleTransactions = listOf(
    Transaction(
        name = "Apple Store",
        productType = ProductType.ENTERTAINMENT,
        iconSource = R.drawable.apple,
        type = TransactionType.SENT,
        price = 5.99,
    ),
    Transaction(
        name = "Spotify",
        productType = ProductType.MUSIC,
        iconSource = R.drawable.spotify,
        type = TransactionType.SENT,
        price = 12.99,
    ),
    Transaction(
        name = "Money Transfer",
        productType = ProductType.TRANSACTION,
        iconSource = R.drawable.money_transfer,
        type = TransactionType.RECEIVE,
        price = 300.0,
    ),
    Transaction(
        name = "Grocery",
        productType = ProductType.SHOPPING,
        iconSource = R.drawable.grocery,
        type = TransactionType.SENT,
        price = 88.0,
    ),
    Transaction(
        name = "Netflix",
        productType = ProductType.ENTERTAINMENT,
        iconSource = R.drawable.netflix,
        type = TransactionType.SENT,
        price = 5.99,
    ),
)

val transactions = sampleTransactions + sampleTransactions + sampleTransactions + sampleTransactions

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeBankScreen(
    onSeeAll: () -> Unit
) {
    val configuration = LocalConfiguration.current
    val screenWith = configuration.screenWidthDp
    val listState = rememberLazyListState()
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            HomeTopAppBar()
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
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    HomeIconButton(
                        iconSource = R.drawable.sent,
                        title = "Sent"
                    )
                    HomeIconButton(
                        iconSource = R.drawable.receive,
                        title = "Receive"
                    )
                    HomeIconButton(
                        iconSource = R.drawable.loan,
                        title = "Loan"
                    )
                    HomeIconButton(
                        iconSource = R.drawable.topup,
                        title = "Topup"
                    )
                }
                Spacer(modifier = Modifier.height(28.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Transaction",
                        fontSize = 18.sp,
                        lineHeight = 18.sp,
                        fontWeight = FontWeight(500),
                        color = Color(0xFFFFFFFF),
                    )
                    TextButton(
                        onClick = {
                            showBottomSheet = true
                        },
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Text(
                            text = "See All",
                            fontSize = 14.sp,
                            lineHeight = 14.sp,
                            fontWeight = FontWeight(500),
                        )
                    }
                }
                LazyColumn(
                    state = listState,
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    items(transactions.size){index ->
                        val item = transactions[index]
                        TransactionItem(item)
                    }
                }
            }
            Image(
                painter = painterResource(id = R.drawable.card_top_stack),
                contentDescription = "Card Top Stack",
                modifier = Modifier
                    .width((screenWith * 270 / 375).dp)
                    .height((screenWith * 361 / 375).dp)
                    .offset((screenWith / 2.5).dp, (screenWith / 2.5).dp)
            )
        }
        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = {
                    showBottomSheet = false
                },
                sheetState = sheetState,
                windowInsets = WindowInsets(0.dp, 116.dp, 0.dp, 0.dp),
                containerColor = Color(0xFF1E1E2D)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 20.dp)
                ) {
                    Text(
                        text = "Category Chart",
                        fontSize = 18.sp,
                        lineHeight = 18.sp,
                        fontWeight = FontWeight(500),
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(29.dp))
                    Image(
                        painter = painterResource(id = R.drawable.indicator_chart),
                        contentDescription = "Transaction Indicator Chart",
                        modifier = Modifier
                            .width(197.dp)
                            .height(196.dp)
                    )
                    Spacer(modifier = Modifier.height(32.dp))
                    FootNote()
                    Spacer(modifier = Modifier.height(29.dp))
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Transaction History",
                            fontSize = 18.sp,
                            lineHeight = 18.sp,
                            fontWeight = FontWeight(500),
                            color = Color(0xFFFFFFFF),
                        )
                        TextButton(
                            onClick = {
                                onSeeAll()
//                                scope.launch {
//                                    sheetState.hide()
//                                    onSeeAll()
//                                }.invokeOnCompletion {
//                                    if (!sheetState.isVisible) {
//                                        showBottomSheet = false
//                                    }
//                                }
                            },
                            contentPadding = PaddingValues(0.dp)
                        ) {
                            Text(
                                text = "See All",
                                fontSize = 14.sp,
                                lineHeight = 14.sp,
                                fontWeight = FontWeight(500),
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    LazyColumn(
                        state = listState,
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(vertical = 10.dp)
                    ) {
                        item {
                            Text(
                                text = "Today",
                                fontSize = 14.sp,
                                lineHeight = 14.sp,
                                fontWeight = FontWeight(500),
                                color = Color(0xFFA2A2A7)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                        items(2){index ->
                            val item = transactions[index]
                            TransactionItem(item)
                        }
                        item {
                            Spacer(modifier = Modifier.height(12.dp))
                            Text(
                                text = "Last 7 Day",
                                fontSize = 14.sp,
                                lineHeight = 14.sp,
                                fontWeight = FontWeight(500),
                                color = Color(0xFFA2A2A7)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                        }
                        items(transactions.size){index ->
                            val item = transactions[index]
                            TransactionItem(item)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun HomeTopAppBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp, 10.dp, 20.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.user_avatar),
            contentDescription = "Avatar",
            modifier = Modifier
                .width(50.dp)
                .height(50.dp)
                .border(
                    width = 0.dp,
                    color = Color.Transparent,
                    shape = CircleShape,
                )
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "Welcome back,",
                fontSize = 12.sp,
                lineHeight = 12.sp,
                color = Color(0xFF7E848D)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Tanya Myroniuk",
                fontSize = 18.sp,
                lineHeight = 18.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFFFFFFFF)
            )
        }
        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .width(42.dp)
                .height(42.dp)
                .border(
                    0.dp,
                    Color.Transparent,
                    CircleShape,
                ),
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = Color(0xFF1E1E2D),
            )
        ) {
            Image(
                painter = painterResource(id = R.drawable.search),
                contentDescription = "Search Button",
                modifier = Modifier
                    .width(20.dp)
                    .height(20.dp),
            )
        }
    }
}



@Composable
private fun HomeIconButton(
    iconSource: Int,
    title: String,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .width(54.dp)
                .height(54.dp)
                .border(
                    0.dp,
                    Color.Transparent,
                    CircleShape,
                ),
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = Color(0xFF1E1E2D),
            )
        ) {
            Image(
                painter = painterResource(id = iconSource),
                contentDescription = title,
                modifier = Modifier
                    .width(20.dp)
                    .height(20.dp),
            )
        }
        Spacer(modifier = Modifier.height(7.dp))
        Text(
            text = title,
            fontSize = 13.sp,
            lineHeight = 13.sp,
            color = Color(0xFFA2A2A7)
        )
    }
}

@Composable
private fun FootNote() {
    val transactionBrush = Brush.horizontalGradient(
        listOf(
            Color(0xFFFF8F6E),
            Color(0xFFFFCA63),
        )
    )
    val transferBrush = Brush.horizontalGradient(
        listOf(
            Color(0xFFC1FFC8),
            Color(0xFF47DDC2),
        )
    )
    val foodBrush = Brush.horizontalGradient(
        listOf(
            Color(0xFFC1C8FF),
            Color(0xFFFF6393),
        )
    )
    val travelBrush = Brush.radialGradient(
        listOf(
            Color(0xFFC172FF),
            Color(0xFF08E6F8),
        ),
        radius = 0.5F
    )
    Row {
        Column(
            modifier = Modifier.weight(5F)
        ) {
            FootNoteItem(
                brush = transactionBrush,
                title = "Transaction"
            )
            Spacer(modifier = Modifier.height(24.dp))
            FootNoteItem(
                brush = foodBrush,
                title = "Food"
            )
        }
        Spacer(modifier = Modifier.width(44.dp))
        Column(
            modifier = Modifier.weight(4F)
        ) {
            FootNoteItem(
                brush = transferBrush,
                title = "Transfer"
            )
            Spacer(modifier = Modifier.height(24.dp))
            FootNoteItem(
                brush = transactionBrush,
                title = "Shopping"
            )
        }
        Spacer(modifier = Modifier.width(44.dp))
        Column(
            modifier = Modifier.weight(3F)
        ) {
            FootNoteItem(
                brush = travelBrush,
                title = "Travel"
            )
            Spacer(modifier = Modifier.height(24.dp))
            FootNoteItem(
                brush = transferBrush,
                title = "Car"
            )
        }
    }
}

@Composable
private fun FootNoteItem(
    brush: Brush,
    title: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .width(14.dp)
                .height(14.dp)
                .background(
                    brush,
                    shape = RoundedCornerShape(5.dp)
                )
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = title,
            fontSize = 13.sp,
            lineHeight = 13.sp,
            color = Color.White
        )
    }
}
