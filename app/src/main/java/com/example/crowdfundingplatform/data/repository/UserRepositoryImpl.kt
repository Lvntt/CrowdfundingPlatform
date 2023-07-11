package com.example.crowdfundingplatform.data.repository

import com.example.crowdfundingplatform.data.api.ProfileApiService
import com.example.crowdfundingplatform.data.converter.UserConverter
import com.example.crowdfundingplatform.data.datasource.TokenDataSource
import com.example.crowdfundingplatform.data.model.TokenType
import com.example.crowdfundingplatform.domain.entity.User
import com.example.crowdfundingplatform.domain.repository.UserRepository

class UserRepositoryImpl(
    private val profileApiService: ProfileApiService,
    private val tokenDataSource: TokenDataSource
) : UserRepository {
    private val userConverter = UserConverter()

    override suspend fun getYourProfile(): User {
        val accessToken = tokenDataSource.fetchToken(TokenType.ACCESS)!!
        return userConverter.convert(profileApiService.getYourProfile("Bearer $accessToken"))
    }

}