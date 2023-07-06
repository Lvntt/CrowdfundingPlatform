package com.example.crowdfundingplatform.domain.usecase

import com.example.crowdfundingplatform.domain.repository.AuthRepository

class RefreshTokensUseCase(
    private val authRepository: AuthRepository
) {

    suspend fun execute(refreshToken: String) {
        authRepository.refresh(refreshToken)
    }

}