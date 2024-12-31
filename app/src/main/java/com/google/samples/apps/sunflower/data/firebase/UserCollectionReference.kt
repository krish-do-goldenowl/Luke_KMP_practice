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

package com.google.samples.apps.sunflower.data.firebase

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.google.samples.apps.sunflower.data.MUser
import kotlinx.coroutines.tasks.await


class UserCollectionReference {
    private val db = Firebase.firestore
    val collection = db.collection("user")

    suspend fun getUserById(userId: String) : MUser? {
        return try {
            val task = collection.document(userId).get()
            val response =  task .await()
            if (task.isSuccessful){
                response.toObject<MUser>()
            } else {
                Log.d("Error", "getUserById: ${task.exception?.message}")
                null
            }
        } catch (e: Exception) {
            Log.d("Exception", "getUserById: ${e.message}")
            null
        }
    }

    suspend fun addUser(user: MUser): Boolean {
        return try {
            val task = collection.add(user)
            task.await()
            if (task.isSuccessful) {
                true
            } else {
                Log.d("Error", "addUser: ${task.exception?.message}")
                false
            }
        } catch (e: Exception) {
            Log.d("Exception", "addUser: ${e.message}")
            false
        }
    }

    suspend fun updateUser(user: MUser): Boolean {
        return try {
            val task = collection.document(user.userId).set(user)
            task.await()
            if (task.isSuccessful) {
                true
            } else {
                Log.d("Error", "updateUser: ${task.exception?.message}")
                false
            }
        } catch (e: Exception) {
            Log.d("Exception", "updateUser: ${e.message}")
            false
        }
    }
}