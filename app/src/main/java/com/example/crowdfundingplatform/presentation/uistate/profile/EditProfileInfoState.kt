package com.example.crowdfundingplatform.presentation.uistate.profile

import androidx.annotation.StringRes

sealed interface EditProfileInfoState {
    object Loading : EditProfileInfoState

    object SignedOut : EditProfileInfoState

    object Success : EditProfileInfoState

    object Input : EditProfileInfoState

    data class Error(@StringRes val messageId: Int) : EditProfileInfoState
}