package com.example.crowdfundingplatform.domain.usecase

import com.example.crowdfundingplatform.domain.entity.EditProfileRequest
import com.example.crowdfundingplatform.domain.entity.User
import com.example.crowdfundingplatform.domain.repository.UserRepository

class EditYourProfileUseCase(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(editProfileRequest: EditProfileRequest): User =
        userRepository.editYourProfile(editProfileRequest)

}