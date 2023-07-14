package com.example.crowdfundingplatform.domain.repository

import com.example.crowdfundingplatform.domain.entity.auth.LoginRequest
import com.example.crowdfundingplatform.domain.entity.auth.RegisterRequest

interface AuthRepository {

    suspend fun register(body: RegisterRequest)

    suspend fun login(body: LoginRequest)

    suspend fun refresh()

    suspend fun logout()

    suspend fun hasTokens(): Boolean

}