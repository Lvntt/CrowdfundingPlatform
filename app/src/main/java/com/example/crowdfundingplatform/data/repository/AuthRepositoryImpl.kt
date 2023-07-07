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
        tokenDataSource.saveToken(tokenResponse.accessToken, TokenType.ACCESS)
        tokenDataSource.saveToken(tokenResponse.refreshToken, TokenType.REFRESH)
    }

    override suspend fun login(body: LoginRequest) {
        val tokenResponse = crowdfundingApiService.login(body)
        tokenDataSource.saveToken(tokenResponse.accessToken, TokenType.ACCESS)
        tokenDataSource.saveToken(tokenResponse.refreshToken, TokenType.REFRESH)
    }

    override suspend fun refresh() {
        val refreshToken = tokenDataSource.fetchToken(TokenType.REFRESH)!!
        val tokenResponse = crowdfundingApiService.refresh(refreshToken)
        tokenDataSource.saveToken(tokenResponse.accessToken, TokenType.ACCESS)
        tokenDataSource.saveToken(tokenResponse.refreshToken, TokenType.REFRESH)
    }

    override suspend fun logout() {
        val accessToken = tokenDataSource.fetchToken(TokenType.ACCESS)!!
        val refreshToken = tokenDataSource.fetchToken(TokenType.REFRESH)!!
        crowdfundingApiService.logout("Bearer $accessToken", refreshToken)
    }

}