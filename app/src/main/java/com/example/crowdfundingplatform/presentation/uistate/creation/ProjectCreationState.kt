package com.example.crowdfundingplatform.presentation.uistate.creation

sealed interface ProjectCreationState {

    object Input : ProjectCreationState

    object Loading : ProjectCreationState

    object Success : ProjectCreationState

}