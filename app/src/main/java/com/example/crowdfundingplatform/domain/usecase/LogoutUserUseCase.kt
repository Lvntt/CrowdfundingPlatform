package com.example.crowdfundingplatform.domain.usecase

import com.example.crowdfundingplatform.domain.repository.AuthRepository

class LogoutUserUseCase(
    private val authRepository: AuthRepository
) {

    suspend fun execute(refreshToken: String) {
        authRepository.logout(refreshToken)
    }

}