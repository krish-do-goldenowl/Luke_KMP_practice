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

package com.google.samples.apps.sunflower.compose.transactionhistory

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.samples.apps.sunflower.R
import com.google.samples.apps.sunflower.compose.homebank.TransactionItem
import com.google.samples.apps.sunflower.compose.homebank.transactions
import com.google.samples.apps.sunflower.compose.utils.BankTopAppBar


@Composable
fun TransactionHistoryScreen(
    onBack: () -> Unit,
    onSearchTransaction: () -> Unit
) {
    val listState = rememberLazyListState()

    Scaffold(
        modifier = Modifier.safeDrawingPadding(),
        topBar = {
            BankTopAppBar(
                title = "Transaction History",
                onBack = onBack,
                iconSource = R.drawable.history,
                onActionButton = onSearchTransaction
            )
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(20.dp, contentPadding.calculateTopPadding(), 20.dp, 0.dp)
                .fillMaxSize(),
        ) {
            Spacer(modifier = Modifier.height(30.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Today",
                    fontSize = 18.sp,
                    lineHeight = 18.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFFFFFFFF),
                )
                TextButton(
                    onClick = { },
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
            Spacer(modifier = Modifier.height(18.dp))
            LazyColumn(
                state = listState,
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(0.dp)
            ) {
                items(transactions.size){ index ->
                    val item = transactions[index]
                    TransactionItem(item)
                }
            }
        }
    }
}