package com.example.crowdfundingplatform.data.remote.model

data class UserModel(
    val id: String,
    val personRole: PersonRole?,
    val name: String?,
    val surname: String?,
    val patronymic: String?,
    val email: String?,
    val money: Int?,
    val bio: String?
)
