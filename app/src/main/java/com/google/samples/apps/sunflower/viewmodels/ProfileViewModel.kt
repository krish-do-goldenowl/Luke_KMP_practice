/*
 * Copyright 2024 Google LLC
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

package com.google.samples.apps.sunflower.viewmodels

import androidx.lifecycle.ViewModel import com.google.samples.apps.sunflower.data.MUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor() : ViewModel() {

    private val _userProfile = MutableStateFlow(MUser())
    val userProfile = _userProfile.asStateFlow()

    fun setUserProfile(value: MUser) {
        _userProfile.update {
            value
        }
    }

    fun setUserName(value: String) {
        val user = userProfile.value.copy(
            userName = value
        )
        setUserProfile(user)
    }

    fun setPhotoUrl(value: String) {
        val user = userProfile.value.copy(
            profilePictureUrl = value
        )
        setUserProfile(user)
    }
}