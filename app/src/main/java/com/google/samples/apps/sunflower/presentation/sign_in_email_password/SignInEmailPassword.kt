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

package com.google.samples.apps.sunflower.presentation.sign_in_email_password

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.samples.apps.sunflower.data.MUser
import com.google.samples.apps.sunflower.presentation.google_sign_in.SignInResult
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.tasks.await

class SignInEmailPassword {
    private val auth = Firebase.auth

    suspend fun createAccountWithEmail(email: String, password: String): SignInResult {
        return try {
            var result: SignInResult
            val task = auth.createUserWithEmailAndPassword(email, password)
            val authResult = task.await()
            val user = authResult.user?.run {
                MUser(
                    userId = this.uid,
                    email = this.email,
                    userName = this.displayName,
                    profilePictureUrl = this.photoUrl?.toString(),
                )
            }
            result = if (user?.userId?.isNotEmpty() == true){
                SignInResult(
                    data = user,
                    errorMessage = null
                )
            } else {
                SignInResult(
                    data = null,
                    errorMessage = task.exception?.message ?: "Unknown Error"
                )
            }
            result
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw  e
            SignInResult(
                data = null,
                errorMessage = e.message ?: "Unknown error"
            )
        }
    }

    suspend fun signInWithEmail(email: String, password: String): SignInResult {
        return try {
            var result: SignInResult
            val task = auth.signInWithEmailAndPassword(email, password)
            val authResult = task.await()
            val user = authResult.user?.run {
                MUser(
                    userId = this.uid,
                    email = this.email,
                    userName = this.displayName,
                    profilePictureUrl = this.photoUrl?.toString(),
                )
            }
            result = if (user?.userId?.isNotEmpty() == true){
                SignInResult(
                    data = user,
                    errorMessage = null
                )
            } else {
                SignInResult(
                    data = null,
                    errorMessage = task.exception?.message ?: "Unknown Error"
                )
            }
            result
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw  e
            SignInResult(
                data = null,
                errorMessage = e.message ?: "Unknown error"
            )
        }
    }
}
