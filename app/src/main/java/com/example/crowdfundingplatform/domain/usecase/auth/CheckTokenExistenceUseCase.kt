package com.example.crowdfundingplatform.domain.usecase.auth

import com.example.crowdfundingplatform.domain.repository.AuthRepository

class CheckTokenExistenceUseCase(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(): Boolean {
        return authRepository.hasTokens()
    }

}