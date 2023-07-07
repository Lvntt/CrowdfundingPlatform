package com.example.crowdfundingplatform.data.api

import com.example.crowdfundingplatform.common.Constants
import com.example.crowdfundingplatform.domain.entity.LoginRequest
import com.example.crowdfundingplatform.domain.entity.RegisterRequest
import com.example.crowdfundingplatform.domain.entity.TokenResponse
import com.example.crowdfundingplatform.domain.entity.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface CrowdfundingApiService {

    @POST(Constants.REGISTER_URL)
    suspend fun register(@Body body: RegisterRequest): TokenResponse

    @POST(Constants.LOGIN_URL)
    suspend fun login(@Body body: LoginRequest): TokenResponse

    @POST(Constants.LOGOUT_URL)
    suspend fun logout(@Header("Authorization") accessToken: String, @Body refreshToken: String)

    @POST(Constants.REFRESH_URL)
    suspend fun refresh(@Body refreshToken: String): TokenResponse

    @GET(Constants.YOUR_PROFILE_URL)
    suspend fun getYourProfile(@Header("Authorization") accessToken: String): User

}