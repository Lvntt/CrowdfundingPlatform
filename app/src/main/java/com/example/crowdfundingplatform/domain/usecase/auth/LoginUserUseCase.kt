package com.example.crowdfundingplatform.domain.usecase.auth

import com.example.crowdfundingplatform.domain.entity.auth.LoginRequest
import com.example.crowdfundingplatform.domain.repository.AuthRepository

class LoginUserUseCase(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(body: LoginRequest) {
        authRepository.login(body)
    }

}