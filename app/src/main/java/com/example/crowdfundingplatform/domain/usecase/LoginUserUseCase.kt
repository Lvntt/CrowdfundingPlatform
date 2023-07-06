package com.example.crowdfundingplatform.domain.usecase

import com.example.crowdfundingplatform.domain.entity.LoginRequest
import com.example.crowdfundingplatform.domain.repository.AuthRepository

class LoginUserUseCase(
    private val authRepository: AuthRepository
) {

    suspend fun execute(body: LoginRequest) {
        authRepository.login(body)
    }

}