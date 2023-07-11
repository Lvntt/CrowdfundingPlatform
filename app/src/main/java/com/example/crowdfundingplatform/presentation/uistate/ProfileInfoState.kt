package com.example.crowdfundingplatform.presentation.uistate

import androidx.annotation.StringRes
import com.example.crowdfundingplatform.domain.entity.User

sealed interface ProfileInfoState {

    object Loading : ProfileInfoState

    object SignedOut : ProfileInfoState

    data class Content(val user: User) : ProfileInfoState

    data class Error(@StringRes val messageId: Int) : ProfileInfoState

}