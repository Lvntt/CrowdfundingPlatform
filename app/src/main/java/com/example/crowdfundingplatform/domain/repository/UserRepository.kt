package com.example.crowdfundingplatform.domain.repository

import com.example.crowdfundingplatform.domain.entity.user.EditProfileRequest
import com.example.crowdfundingplatform.domain.entity.user.User

interface UserRepository {

    suspend fun getYourProfile(): User

    suspend fun editYourProfile(editProfileRequest: EditProfileRequest): User

}