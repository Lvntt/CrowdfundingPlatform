package com.example.crowdfundingplatform.domain.entity.user

import java.math.BigDecimal

data class User(
    val id: String,
    val userRole: UserRole,
    val name: String,
    val surname: String,
    val patronymic: String,
    val email: String,
    val money: BigDecimal,
    val bio: String,
    val emailIsConfirmed: Boolean,
    val avatarId: String?
)
