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
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.samples.apps.sunflower.ui.md_theme_light_primary


@Composable
fun TransactionItem(item: Transaction) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 11.dp)
    ) {
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
                painter = painterResource(id = item.iconSource),
                contentDescription = item.name,
                modifier = Modifier
                    .width(20.dp)
                    .height(20.dp),
            )
        }
        Spacer(modifier = Modifier.width(17.dp))
        Column {
            Text(
                text = item.name,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFFFFFFFF),
            )
            Text(
                text = item.productType.title,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                color = Color(0xFFFFFFFF),
                modifier = Modifier.alpha(0.5F)
            )
        }
        Spacer(modifier = Modifier.weight(1F))
        Text(
            text = "${if(item.type == TransactionType.SENT) "- " else ""}$${item.price}",
            fontSize = 16.sp,
            lineHeight = 16.sp,
            fontWeight = FontWeight(500),
            color = if(item.type == TransactionType.SENT) Color(0xFFFFFFFF) else md_theme_light_primary,
        )
    }
}