package com.example.crowdfundingplatform.presentation.uistate

import androidx.annotation.StringRes

sealed interface AuthUiState {
    object Input : AuthUiState

    object Loading : AuthUiState

    data class Error(
        @StringRes val messageId: Int
    ) : AuthUiState

    object Success : AuthUiState
}