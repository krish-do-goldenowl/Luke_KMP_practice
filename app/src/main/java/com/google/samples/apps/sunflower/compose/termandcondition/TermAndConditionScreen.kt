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

package com.google.samples.apps.sunflower.compose.termandcondition

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.samples.apps.sunflower.compose.utils.BankTopAppBar

@Composable
fun TermAndConditionScreen(
    onBack: () -> Unit = {},
) {
    Scaffold(
        modifier = Modifier.safeDrawingPadding(),
        topBar = {
            BankTopAppBar(
                title = "Terms & Condition",
                onBack = onBack,
            )
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(20.dp, contentPadding.calculateTopPadding(), 20.dp, 0.dp)
                .fillMaxSize(),
        ) {
            Spacer(modifier = Modifier.height(34.dp))
            Text(
                text = "L15.1 Thank you for visiting our Application Doctor 24×7 and enrolling as a member.",
                fontSize = 14.sp,
                lineHeight = 24.sp,
                color = Color(0xFFA2A2A7)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "15.2 Your privacy is important to us. To better protect your privacy, we are providing this notice explaining our policy with regards to the information you share with us. This privacy policy related to the information we collect, online from Application, received through the email, by fax or telephone, or in person or in any other way and retain and use for the purpose of providing you services. If you do not agree to the terms in this Policy, we kindly ask you not to use these portals and/or sign the contract document.",
                fontSize = 14.sp,
                lineHeight = 24.sp,
                color = Color(0xFFA2A2A7)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "15.3 In order to use the services of this Application, You are required to register yourself by verifying the authorised device. This Privacy Policy applies to your information that we collect and receive on and through Doctor 24×7; it does not apply to practices of businesses that we do not own or control or people we do not employ.",
                fontSize = 14.sp,
                lineHeight = 24.sp,
                color = Color(0xFFA2A2A7)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "15.4 By using this Application, you agree to the terms of this Privacy Policy.",
                fontSize = 14.sp,
                lineHeight = 24.sp,
                color = Color(0xFFA2A2A7)
            )
        }
    }
}