package com.example.crowdfundingplatform.presentation.uistate

import androidx.annotation.StringRes

sealed interface FundProjectState {

    object Loading : FundProjectState

    object Success : FundProjectState

    object SignedOut : FundProjectState

    data class Input(val moneyAmount: String) : FundProjectState

    data class Error(@StringRes val messageId: Int) : FundProjectState

}