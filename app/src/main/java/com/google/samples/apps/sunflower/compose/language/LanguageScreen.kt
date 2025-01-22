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

package com.google.samples.apps.sunflower.compose.language

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.samples.apps.sunflower.R
import com.google.samples.apps.sunflower.compose.utils.BankTopAppBar
import com.google.samples.apps.sunflower.ui.md_theme_light_primary
import okhttp3.internal.filterList

data class LanguageData(
    val name: String,
    val iconSource: Int,
)

val listLanguage = listOf(
    LanguageData(
        name = "English",
        iconSource = R.drawable.english,
    ),
    LanguageData(
        name = "Australia",
        iconSource = R.drawable.australia,
    ),
    LanguageData(
        name = "Francg",
        iconSource = R.drawable.franch,
    ),
    LanguageData(
        name = "Spanish",
        iconSource = R.drawable.spanish,
    ),
    LanguageData(
        name = "America",
        iconSource = R.drawable.america,
    ),
    LanguageData(
        name = "Vietnam",
        iconSource = R.drawable.vietnam,
    )
)

@Composable
fun LanguageScreen(
    onBack: () -> Unit = {},
) {
    var currentLanguage by remember {
        mutableStateOf(value = LanguageData(
            name = "English",
            iconSource = R.drawable.english,
        ))
    }
    var searchText by remember {
        mutableStateOf(value = "")
    }
    val listLanguageFiltered = listLanguage.filterList {
        this.name.lowercase().contains(searchText.lowercase())
    }

    Scaffold(
        modifier = Modifier.safeDrawingPadding(),
        topBar = {
            BankTopAppBar(
                title = "Language",
                onBack = onBack,
            )
        }
    ) {it ->
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(it)
                .padding(20.dp),
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
                        text = "Search Language",
                        color = Color(0xFFBBBBC1),
                        fontSize = 14.sp,
                        lineHeight = 14.sp
                    )
                },
                leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.search),
                        contentDescription = "Search Location",
                        colorFilter = ColorFilter.tint(color = Color(0xFFBBBBC1))
                    )
                },
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFF232533),
                    unfocusedContainerColor = Color(0xFF232533),
                    errorContainerColor =Color(0xFF232533),
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
            Spacer(modifier = Modifier.height(21.dp))
            listLanguageFiltered.forEach {item ->
                LanguageItem(
                    language = item,
                    currentLanguage = currentLanguage,
                    onValueChange = {
                        currentLanguage = item
                    }
                )
            }
        }
    }
}

@Composable
private fun LanguageItem(
    language: LanguageData,
    currentLanguage: LanguageData,
    onValueChange: () -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(vertical = 11.dp)
    ) {
        Button(
            onClick = onValueChange,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
            ),
            contentPadding = PaddingValues(0.dp),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 0.dp,
                pressedElevation = 0.dp,
                focusedElevation = 0.dp,
                hoveredElevation = 0.dp,
                disabledElevation = 0.dp,
            )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = language.iconSource),
                    contentDescription = "Language ${language.name}",
                    modifier = Modifier
                        .width(48.dp)
                        .height(48.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = language.name,
                    fontSize = 16.sp,
                    lineHeight = 16.sp,
                    fontWeight = FontWeight(500),
                    color = Color.White,
                    modifier = Modifier.weight(1F)
                )
                if(language.name == currentLanguage.name)
                    Box(
                        modifier = Modifier
                            .background(
                                color = md_theme_light_primary,
                                shape = CircleShape
                            )
                            .width(18.dp)
                            .height(18.dp)
                            .padding(2.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Check,
                            contentDescription = "Check Icon"
                        )
                    }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        HorizontalDivider(
            modifier = Modifier.height(1.dp),
            thickness = 1.dp,
            color = Color(0xFF232533)
        )
    }
}
