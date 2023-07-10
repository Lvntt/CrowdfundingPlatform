package com.example.crowdfundingplatform.presentation.uistate

data class RegistrationContent(
    val name: String,
    val surname: String,
    val patronymic: String,
    val email: String,
    val password: String,
    val confirmPassword: String
)
