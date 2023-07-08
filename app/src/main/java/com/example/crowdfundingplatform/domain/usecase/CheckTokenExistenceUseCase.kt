package com.example.crowdfundingplatform.domain.usecase

import com.example.crowdfundingplatform.domain.repository.AuthRepository

class CheckTokenExistenceUseCase(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(): Boolean {
        return authRepository.hasTokens()
    }

}