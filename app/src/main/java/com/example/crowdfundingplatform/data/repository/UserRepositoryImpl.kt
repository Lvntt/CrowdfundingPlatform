package com.example.crowdfundingplatform.data.repository

import com.example.crowdfundingplatform.data.remote.api.UserApiService
import com.example.crowdfundingplatform.data.datasource.TokenDataSource
import com.example.crowdfundingplatform.data.remote.model.TokenType
import com.example.crowdfundingplatform.domain.entity.User
import com.example.crowdfundingplatform.domain.repository.UserRepository

class UserRepositoryImpl(
    private val userApiService: UserApiService,
    private val tokenDataSource: TokenDataSource
) : UserRepository {

    override suspend fun getYourProfile(): User {
        val accessToken = tokenDataSource.fetchToken(TokenType.ACCESS)!!
        return userApiService.getYourProfile("Bearer $accessToken")
    }

}