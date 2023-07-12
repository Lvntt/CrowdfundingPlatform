package com.example.crowdfundingplatform.data.repository

import com.example.crowdfundingplatform.data.remote.api.AuthApiService
import com.example.crowdfundingplatform.data.datasource.TokenDataSource
import com.example.crowdfundingplatform.data.remote.model.TokenType
import com.example.crowdfundingplatform.data.remote.model.RefreshRequest
import com.example.crowdfundingplatform.domain.entity.LoginRequest
import com.example.crowdfundingplatform.domain.entity.RegisterRequest
import com.example.crowdfundingplatform.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val authApiService: AuthApiService,
    private val tokenDataSource: TokenDataSource
) : AuthRepository {

    override suspend fun register(body: RegisterRequest) {
        val tokenResponse = authApiService.register(body)
        tokenDataSource.saveToken(tokenResponse.accessToken, TokenType.ACCESS)
        tokenDataSource.saveToken(tokenResponse.refreshToken, TokenType.REFRESH)
    }

    override suspend fun login(body: LoginRequest) {
        val tokenResponse = authApiService.login(body)
        tokenDataSource.saveToken(tokenResponse.accessToken, TokenType.ACCESS)
        tokenDataSource.saveToken(tokenResponse.refreshToken, TokenType.REFRESH)
    }

    override suspend fun refresh() {
        val refreshToken = tokenDataSource.fetchToken(TokenType.REFRESH)!!
        val tokenResponse = authApiService.refresh(RefreshRequest(refreshToken))
        tokenDataSource.saveToken(tokenResponse.accessToken, TokenType.ACCESS)
        tokenDataSource.saveToken(tokenResponse.refreshToken, TokenType.REFRESH)
    }

    override suspend fun logout() {
        val accessToken = tokenDataSource.fetchToken(TokenType.ACCESS)!!
        val refreshToken = tokenDataSource.fetchToken(TokenType.REFRESH)!!
        authApiService.logout("Bearer $accessToken", refreshToken)
    }

    override suspend fun hasTokens(): Boolean {
        val accessToken = tokenDataSource.fetchToken(TokenType.ACCESS)
        val refreshToken = tokenDataSource.fetchToken(TokenType.REFRESH)
        return accessToken != null && refreshToken != null
    }

}