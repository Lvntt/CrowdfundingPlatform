package com.example.crowdfundingplatform.domain.usecase.auth

import com.example.crowdfundingplatform.domain.repository.AuthRepository

class RefreshTokensUseCase(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke() {
        authRepository.refresh()
    }

}