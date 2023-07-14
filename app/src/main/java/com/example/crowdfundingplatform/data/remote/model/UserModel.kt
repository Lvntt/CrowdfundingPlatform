package com.example.crowdfundingplatform.data.remote.model

import java.math.BigDecimal

data class UserModel(
    val id: String,
    val personRole: PersonRole?,
    val name: String?,
    val surname: String?,
    val patronymic: String?,
    val email: String?,
    val money: BigDecimal?,
    val bio: String?,
    val emailIsConfirm: Boolean?,
    val avatarId: String?
)
