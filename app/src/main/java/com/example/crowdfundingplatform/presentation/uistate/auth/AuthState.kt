package com.example.crowdfundingplatform.presentation.uistate.auth

sealed interface AuthState {

    object Input : AuthState

    object Loading : AuthState

    object Success : AuthState

}