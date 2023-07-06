package com.example.crowdfundingplatform.data.repository

import com.example.crowdfundingplatform.data.api.CrowdfundingApiService
import com.example.crowdfundingplatform.domain.entity.User
import com.example.crowdfundingplatform.domain.repository.UserRepository

class UserRepositoryImpl(
    private val crowdfundingApiService: CrowdfundingApiService
) : UserRepository {

    override suspend fun getYourProfile(accessToken: String): User {
        return crowdfundingApiService.getYourProfile("Bearer $accessToken")
    }

}