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

import androidx.lifecycle.ViewModel
import com.google.samples.apps.sunflower.data.MUser
import com.google.samples.apps.sunflower.data.MUserRepository
import com.google.samples.apps.sunflower.presentation.google_sign_in.SignInResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor() : ViewModel() {
    private val userRepository: MUserRepository = MUserRepository()
    private val _user = MutableStateFlow(MUser())
    val user = _user.asStateFlow()

    suspend fun getUser(userResult: MUser){
        val user = userRepository.getUser(userResult.userId)
        setUser(user ?: userResult , updateFirestore = user == null)
    }

    suspend fun onSignInResult(result: SignInResult) {
        if (result.data != null) {
            getUser(result.data)
        }
    }

    suspend fun setUser(user: MUser, updateFirestore: Boolean = false) {
        if(updateFirestore){
            userRepository.updateUser(user)
        }
        _user.update { user }
    }

    suspend fun resetUser() {
        setUser(MUser())
    }
}