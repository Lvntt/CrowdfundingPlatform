package com.example.crowdfundingplatform.domain.usecase

import com.example.crowdfundingplatform.domain.entity.User
import com.example.crowdfundingplatform.domain.repository.UserRepository

class GetYourProfileUseCase(
    private val userRepository: UserRepository
) {

    suspend fun execute(accessToken: String): User {
        return userRepository.getYourProfile(accessToken)
    }

}