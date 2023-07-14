package com.example.crowdfundingplatform.domain.entity.auth

data class LoginRequest(
    val email: String,
    val password: String
)
