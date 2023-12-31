package com.example.crowdfundingplatform.data.repository

import com.example.crowdfundingplatform.data.remote.api.UserApiService
import com.example.crowdfundingplatform.data.converter.UserConverter
import com.example.crowdfundingplatform.data.datasource.TokenDataSource
import com.example.crowdfundingplatform.data.remote.model.TokenType
import com.example.crowdfundingplatform.domain.entity.user.EditProfileRequest
import com.example.crowdfundingplatform.domain.entity.user.User
import com.example.crowdfundingplatform.domain.repository.UserRepository

class UserRepositoryImpl(
    private val userApiService: UserApiService,
    private val tokenDataSource: TokenDataSource,
    private val userConverter: UserConverter
) : UserRepository {

    override suspend fun getYourProfile(): User {
        val accessToken = tokenDataSource.fetchToken(TokenType.ACCESS)!!
        return userConverter.convert(userApiService.getYourProfile("Bearer $accessToken"))
    }

    override suspend fun editYourProfile(editProfileRequest: EditProfileRequest): User {
        val accessToken = tokenDataSource.fetchToken(TokenType.ACCESS)!!
        return userConverter.convert(userApiService.editYourProfile("Bearer $accessToken", editProfileRequest))
    }

}