package com.example.crowdfundingplatform.domain.entity.user

data class EditProfileRequest(
    val name: String,
    val surname: String,
    val patronymic: String,
    val bio: String,
    val avatarId: String?
)
