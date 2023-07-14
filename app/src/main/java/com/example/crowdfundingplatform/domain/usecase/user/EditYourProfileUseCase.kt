package com.example.crowdfundingplatform.domain.usecase.user

import com.example.crowdfundingplatform.domain.entity.user.EditProfileRequest
import com.example.crowdfundingplatform.domain.entity.user.User
import com.example.crowdfundingplatform.domain.repository.UserRepository

class EditYourProfileUseCase(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(editProfileRequest: EditProfileRequest): User =
        userRepository.editYourProfile(editProfileRequest)

}