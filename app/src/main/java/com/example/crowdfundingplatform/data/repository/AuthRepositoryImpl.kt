package com.example.crowdfundingplatform.data.repository

import com.example.crowdfundingplatform.data.api.CrowdfundingApiService
import com.example.crowdfundingplatform.data.datasource.TokenDataSource
import com.example.crowdfundingplatform.data.model.TokenType
import com.example.crowdfundingplatform.domain.entity.LoginRequest
import com.example.crowdfundingplatform.domain.entity.RegisterRequest
import com.example.crowdfundingplatform.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val crowdfundingApiService: CrowdfundingApiService,
    private val tokenDataSource: TokenDataSource
) : AuthRepository {

    override suspend fun register(body: RegisterRequest) {
        val tokenResponse = crowdfundingApiService.register(body)
        tokenDataSource.saveToken(tokenResponse.accessToken, TokenType.Access)
        tokenDataSource.saveToken(tokenResponse.refreshToken, TokenType.Refresh)
    }

    override suspend fun login(body: LoginRequest) {
        val tokenResponse = crowdfundingApiService.login(body)
        tokenDataSource.saveToken(tokenResponse.accessToken, TokenType.Access)
        tokenDataSource.saveToken(tokenResponse.refreshToken, TokenType.Refresh)
    }

    override suspend fun refresh(refreshToken: String) {
        val tokenResponse = crowdfundingApiService.refresh(refreshToken)
        tokenDataSource.saveToken(tokenResponse.accessToken, TokenType.Access)
        tokenDataSource.saveToken(tokenResponse.refreshToken, TokenType.Refresh)
    }

    override suspend fun logout(refreshToken: String) {
        crowdfundingApiService.logout(refreshToken)
    }

}