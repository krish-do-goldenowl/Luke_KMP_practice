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
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.firestore.toObjects
import com.google.samples.apps.sunflower.data.MUser
import com.google.samples.apps.sunflower.data.Plant
import kotlinx.coroutines.tasks.await

class PlantCollectionReference(userId: String) {
    private val db = Firebase.firestore
    val collection = db.collection("user/$userId/plant")

    suspend fun getPlants() : List<Plant>? {
        return try {
            val task = collection.orderBy("createdAt", Query.Direction.DESCENDING) .get()
            val response =  task .await()
            if (task.isSuccessful){
                response.toObjects<Plant>()
            } else {
                Log.d("Error", "getUserById: ${task.exception?.message}")
                null
            }
        } catch (e: Exception) {
            Log.d("Exception", "getUserById: ${e.message}")
            null
        }
    }

    suspend fun getPlantById(plantId: String) : Plant? {
        return try {
            val task = collection.document(plantId).get()
            val response =  task .await()
            if (task.isSuccessful){
                response.toObject<Plant>()
            } else {
                Log.d("Error", "getPlantById: ${task.exception?.message}")
                null
            }
        } catch (e: Exception) {
            Log.d("Exception", "getPlantById: ${e.message}")
            null
        }
    }

    suspend fun addPlant(plant: Plant): Boolean {
        return try {
            val task = collection.add(plant)
            task.await()
            if (task.isSuccessful) {
                true
            } else {
                Log.d("Error", "addPlant: ${task.exception?.message}")
                false
            }
        } catch (e: Exception) {
            Log.d("Exception", "addPlant: ${e.message}")
            false
        }
    }

    suspend fun removePlant(plant: Plant): Boolean {
        return try {
            val task = collection.document(plant.plantId).delete()
            task.await()
            if (task.isSuccessful) {
                true
            } else {
                Log.d("Error", "removePlant: ${task.exception?.message}")
                false
            }
        } catch (e: Exception) {
            Log.d("Exception", "removePlant: ${e.message}")
            false
        }
    }
}
