package com.example.crowdfundingplatform.domain.repository

import com.example.crowdfundingplatform.domain.entity.EditProfileRequest
import com.example.crowdfundingplatform.domain.entity.User

interface UserRepository {

    suspend fun getYourProfile(): User

    suspend fun editYourProfile(editProfileRequest: EditProfileRequest): User

}