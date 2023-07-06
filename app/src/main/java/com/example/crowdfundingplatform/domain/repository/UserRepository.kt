package com.example.crowdfundingplatform.domain.repository

import com.example.crowdfundingplatform.domain.entity.User

interface UserRepository {

    suspend fun getYourProfile(accessToken: String): User

}