package com.example.crowdfundingplatform.presentation.uistate.auth

sealed interface OnboardingState {
    object Initial : OnboardingState

    object Content : OnboardingState

    object Success : OnboardingState
}