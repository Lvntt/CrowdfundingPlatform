package com.example.crowdfundingplatform.domain.entity

data class User(
    val id: String,
    val name: String,
    val surname: String,
    val patronymic: String,
    val email: String,
    val role: String,
    val money: Int,
    val status: String
)
