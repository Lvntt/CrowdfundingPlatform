package com.example.crowdfundingplatform.presentation.uistate

sealed interface AuthState {

    object Input : AuthState

    object Loading : AuthState

    object Success : AuthState

}