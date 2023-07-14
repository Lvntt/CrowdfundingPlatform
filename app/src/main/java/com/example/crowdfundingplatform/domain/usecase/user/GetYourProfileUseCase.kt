package com.example.crowdfundingplatform.domain.usecase.user

import com.example.crowdfundingplatform.domain.entity.user.User
import com.example.crowdfundingplatform.domain.repository.UserRepository

class GetYourProfileUseCase(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(): User {
        return userRepository.getYourProfile()
    }

}