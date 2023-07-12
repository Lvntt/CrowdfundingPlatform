package com.example.crowdfundingplatform.data.remote.api

import com.example.crowdfundingplatform.common.Constants
import com.example.crowdfundingplatform.domain.entity.User
import retrofit2.http.GET
import retrofit2.http.Header

interface UserApiService {

    @GET(Constants.YOUR_PROFILE_URL)
    suspend fun getYourProfile(@Header("Authorization") accessToken: String): User

}