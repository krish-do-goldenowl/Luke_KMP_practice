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

package com.google.samples.apps.sunflower.compose.onboarding1

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.samples.apps.sunflower.R
import com.google.samples.apps.sunflower.data.MUser
import kotlinx.coroutines.launch

data class OnboardingState(
    val imageRes: Int,
    val title: String,
    val subTitle: String,
)

val onboardingImages : List<OnboardingState> = listOf(
    OnboardingState(
        R.drawable.onboarding_1,
        "Fastest Payment in the world",
        "Integrate multiple payment methoods to help you up the process quickly",
    ),
    OnboardingState(
        R.drawable.onboarding_2,
        "The most Secoure Platfrom for Customer",
        "Built-in Fingerprint, face recognition and more, keeping you completely safe",
    ),
    OnboardingState(
        R.drawable.onboarding_3,
        "Paying for Everything is Easy and Convenient",
        "Built-in Fingerprint, face recognition and more, keeping you completely safe",
    )
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(
    onNext: () -> Unit,
) {
    val pagerState = rememberPagerState(pageCount = {
        3
    })
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        modifier = Modifier.imePadding(),
    ) {it ->
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.padding(it),
        ) {page ->
            var state = onboardingImages[page]
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxSize()
            ) {
                Image(
                    painter = painterResource(id = state.imageRes),
                    contentDescription = "Onboarding 1",
                    alignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                )
                Spacer(modifier = Modifier.height(36.dp))
                Text(
                    text = state.title,
                    fontSize = 26.sp,
                    fontWeight = FontWeight(600),
                    textAlign = TextAlign.Center,
                    lineHeight = 39.sp,
                    color = Color(0xFFFFFFFF),
                    maxLines = 2,
                    minLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = state.subTitle,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    lineHeight = 24.sp,
                    color = Color(0xFF7E848D),
                    maxLines = 3,
                    minLines = 3,
                    overflow = TextOverflow.Ellipsis,
                )
                Spacer(modifier = Modifier.height(30.dp))
                Button(
                    onClick = {
                        if(page == onboardingImages.size - 1){
                            onNext()
                        } else {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(page+1)
                            }
                        }
                    },
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)

                ) {
                    Text(
                        "Next",
                        fontSize = 16.sp,
                        fontWeight = FontWeight(600),
                        textAlign = TextAlign.Center,
                        lineHeight = 24.sp,
                    )
                }
                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }
}