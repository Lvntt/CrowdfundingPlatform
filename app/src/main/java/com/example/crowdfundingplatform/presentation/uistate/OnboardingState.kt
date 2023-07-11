package com.example.crowdfundingplatform.presentation.uistate

sealed interface OnboardingState {
    object Initial : OnboardingState

    object Content : OnboardingState

    object Success : OnboardingState
}