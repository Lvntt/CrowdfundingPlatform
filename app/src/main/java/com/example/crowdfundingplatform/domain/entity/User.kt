package com.example.crowdfundingplatform.domain.entity

data class User(
    val id: String,
    val userRole: UserRole,
    val name: String,
    val surname: String,
    val patronymic: String,
    val email: String,
    val money: Int,
    val bio: String,
    val emailIsConfirmed: Boolean,
    val avatarId: String?
)
