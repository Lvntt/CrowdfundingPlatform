package com.example.crowdfundingplatform.presentation.uistate

sealed interface AuthUiState {

    object Initial : AuthUiState

    object Input : AuthUiState

}