package com.example.crowdfundingplatform.presentation.uistate.creation

sealed interface ImageUploadState {

    object Initial : ImageUploadState

    object Success : ImageUploadState

    object Error : ImageUploadState

}