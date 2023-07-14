package com.example.crowdfundingplatform.domain.usecase.auth

import com.example.crowdfundingplatform.domain.repository.AuthRepository

class LogoutUserUseCase(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke() {
        authRepository.logout()
    }

}