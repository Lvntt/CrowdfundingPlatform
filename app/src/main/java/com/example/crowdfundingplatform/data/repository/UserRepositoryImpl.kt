package com.example.crowdfundingplatform.data.repository

import com.example.crowdfundingplatform.data.api.CrowdfundingApiService
import com.example.crowdfundingplatform.data.datasource.TokenDataSource
import com.example.crowdfundingplatform.data.model.TokenType
import com.example.crowdfundingplatform.domain.entity.User
import com.example.crowdfundingplatform.domain.repository.UserRepository

class UserRepositoryImpl(
    private val crowdfundingApiService: CrowdfundingApiService,
    private val tokenDataSource: TokenDataSource
) : UserRepository {

    override suspend fun getYourProfile(): User {
        val accessToken = tokenDataSource.fetchToken(TokenType.Access)!!
        return crowdfundingApiService.getYourProfile("Bearer $accessToken")
    }

}