package com.example.crowdfundingplatform.domain.entity

data class RegisterRequest(
    val name: String,
    val surname: String,
    val patronymic: String,
    val password: String,
    val email: String
)
