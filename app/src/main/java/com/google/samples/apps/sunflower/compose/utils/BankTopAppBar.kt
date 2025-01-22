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

package com.google.samples.apps.sunflower.compose.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.samples.apps.sunflower.R

@Composable
fun BankTopAppBar(
    title: String,
    iconSource: Int? = null,
    hasBackIcon: Boolean = true,
    onBack: () -> Unit = {},
    onActionButton: () -> Unit = {},
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp, 10.dp, 20.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (hasBackIcon)
        {
            IconButton(
                onClick = onBack,
                modifier = Modifier
                    .width(42.dp)
                    .height(42.dp)
            ) {
                Icon(
                    Icons.Filled.KeyboardArrowLeft,
                    contentDescription = stringResource(id = R.string.a11y_back),
                    tint = Color(0xFFFFFFFF),
                    modifier = Modifier.drawBehind {
                        drawCircle(
                            color = Color(0xFF1E1E2D),
                            radius = this.size.maxDimension
                        )
                    }
                )
            }
        } else {
            Spacer(modifier = Modifier.width(42.dp))
        }
        Text(
            text = title,
            fontSize = 18.sp,
            lineHeight = 18.sp,
            color = Color(0xFFFFFFFF),
            fontWeight = FontWeight(500),
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f)
        )
        if (iconSource != null) {
            IconButton(
                onClick = onActionButton,
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
                    painter = painterResource(id = iconSource),
                    contentDescription = "$title Top App Bar $iconSource",
                    modifier = Modifier
                        .width(15.dp),
                )
            }
        } else {
            Spacer(modifier = Modifier.width(42.dp))
        }
    }
}