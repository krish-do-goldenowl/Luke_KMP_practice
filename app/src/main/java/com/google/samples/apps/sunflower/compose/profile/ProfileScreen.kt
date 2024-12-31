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

package com.google.samples.apps.sunflower.compose.profile

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import com.google.samples.apps.sunflower.data.MUser
import com.google.samples.apps.sunflower.mToast
import com.google.samples.apps.sunflower.ui.SunflowerTheme
import com.google.samples.apps.sunflower.viewmodels.ProfileViewModel
import com.google.samples.apps.sunflower.viewmodels.UserViewModel

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    userViewModel: UserViewModel = hiltViewModel(),
    profileViewModel: ProfileViewModel = hiltViewModel(),
    onSave: (MUser) -> Unit = {},
) {
    val mContext = LocalContext.current
    val user by userViewModel.user.collectAsStateWithLifecycle()
    val profile by profileViewModel.userProfile.collectAsStateWithLifecycle()
    LaunchedEffect(key1 = Unit) {
        profileViewModel.setUserProfile(user)
    }
    ProfileScreen(
        modifier = modifier,
        user = user,
        profile = profile,
        onChangeUserName = {value ->
            profileViewModel.setUserName(value)
        },
        onSave = {
            if ((user.userName != profile.userName) || user.profilePictureUrl != profile.profilePictureUrl){
                if (profile.userName?.isEmpty() == true){
                    mToast(mContext, "User Name can't be empty")
                } else {
                    onSave(profile)
                }
            }
        },
    )
}

@Composable
private fun ProfileScreen(
    modifier: Modifier = Modifier,
    user: MUser,
    profile: MUser,
    onChangeUserName: (String) -> Unit = {_ ->},
    onSave: () -> Unit = {},
) {
    var selectedImages by remember {
        mutableStateOf<Uri?>(Uri.parse(user.profilePictureUrl ?: ""))
    }

    val pickImageLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            if(uri?.path?.isNotEmpty() == true){
                selectedImages = uri
            }
        }
    )
    Scaffold(
        modifier
            .imePadding()
            .padding(30.dp)
    ) {it ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxWidth()
                .padding(it),
        ) {
            IconButton(
                onClick = {
                    pickImageLauncher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                },
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .border(
                        width = 1.dp,
                        color = Color(0xFF246D00),
                        shape = RoundedCornerShape(50.dp)
                    ),
            ) {
                Image(
                    painter = rememberAsyncImagePainter(selectedImages),
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            TextField(
                value = profile.userName ?: "",
                placeholder = {
                    Text(
                        text = "User Name",
                    )
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Rounded.Email, contentDescription = "User Name")
                },
                onValueChange = onChangeUserName,
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = Color(0xFF246D00),
                        shape = RoundedCornerShape(50.dp)
                    ),
                shape = RoundedCornerShape(50.dp),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = profile.email ?: "",
                placeholder = {
                    Text(
                        text = "No Email",
                    )
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Rounded.Person, contentDescription = "Email")
                },
                onValueChange = {  },
                enabled = false,
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = Color(0xFF246D00),
                        shape = RoundedCornerShape(50.dp)
                    ),
                shape = RoundedCornerShape(50.dp),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                )
            )
            Spacer(modifier = Modifier.height(50.dp))
            Button(
                onClick = onSave,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = "Save",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

@Preview
@Composable
private fun ProfileScreenPreview(
) {
    SunflowerTheme {
        ProfileScreen()
    }
}

