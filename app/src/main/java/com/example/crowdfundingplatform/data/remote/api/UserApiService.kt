package com.example.crowdfundingplatform.data.remote.api

import com.example.crowdfundingplatform.common.Constants
import com.example.crowdfundingplatform.data.remote.model.UserModel
import retrofit2.http.GET
import retrofit2.http.Header

interface UserApiService {

    @GET(Constants.YOUR_PROFILE_URL)
    suspend fun getYourProfile(@Header("Authorization") accessToken: String): UserModel

}