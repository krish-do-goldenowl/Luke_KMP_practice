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


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "m_user",
//    foreignKeys = [
//        ForeignKey(entity = Plant::class, parentColumns = ["id"], childColumns = ["plant_id"])
//    ],
    indices = [Index("user_id")]
)

data class MUser(
    @PrimaryKey @ColumnInfo(name = "user_id") val userId: String = "",
    @ColumnInfo(name = "user_name") val userName: String? = null,
    @ColumnInfo(name = "email") val email: String? = null,
    @ColumnInfo(name = "profile_picture_url") val profilePictureUrl: String? = null,
)
