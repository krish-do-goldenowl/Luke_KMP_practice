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

package com.google.samples.apps.sunflower.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import dagger.Provides
import kotlinx.coroutines.flow.Flow

@Dao
interface MUserDao {

    @Query("SELECT * FROM m_user")
    fun getUsers(): Flow<List<MUser>>

    @Insert
    suspend fun insertUser(user: MUser): Long

    @Delete
    suspend fun deleteUser(user: MUser)
}