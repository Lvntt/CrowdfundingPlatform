package com.example.crowdfundingplatform.data.converter

import com.example.crowdfundingplatform.common.Constants
import com.example.crowdfundingplatform.data.remote.model.UserModel
import com.example.crowdfundingplatform.domain.entity.user.User
import com.example.crowdfundingplatform.domain.entity.user.UserRole
import java.math.BigDecimal

class UserConverter {
    fun convert(from: UserModel): User =
        with(from) {
            User(
                id = id,
                userRole = if(personRole != null) UserRole.valueOf(personRole.name) else UserRole.NONE,
                name = name ?: Constants.EMPTY_STRING,
                surname = surname ?: Constants.EMPTY_STRING,
                patronymic = patronymic ?: Constants.EMPTY_STRING,
                email = email ?: Constants.EMPTY_STRING,
                money = money ?: BigDecimal.ZERO,
                bio = bio ?: Constants.EMPTY_STRING,
                emailIsConfirmed = emailIsConfirm ?: false,
                avatarId = avatarId
            )
        }
}