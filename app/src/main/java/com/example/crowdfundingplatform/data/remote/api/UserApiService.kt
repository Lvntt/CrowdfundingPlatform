package com.example.crowdfundingplatform.data.remote.api

import com.example.crowdfundingplatform.common.Constants
import com.example.crowdfundingplatform.data.remote.model.UserModel
import com.example.crowdfundingplatform.domain.entity.user.EditProfileRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PUT

interface UserApiService {

    @GET(Constants.YOUR_PROFILE_URL)
    suspend fun getYourProfile(@Header("Authorization") accessToken: String): UserModel

    @PUT(Constants.YOUR_PROFILE_URL)
    suspend fun editYourProfile(@Header("Authorization") accessToken: String, @Body editProfileRequest: EditProfileRequest): UserModel

}