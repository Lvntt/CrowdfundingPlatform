package com.example.crowdfundingplatform.domain.usecase

import com.example.crowdfundingplatform.domain.entity.RegisterRequest
import com.example.crowdfundingplatform.domain.repository.AuthRepository

class RegisterUserUseCase(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(body: RegisterRequest) {
        authRepository.register(body)
    }

}