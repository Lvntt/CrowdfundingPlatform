package com.example.crowdfundingplatform.presentation.uistate

sealed interface AvatarUploadState {

    object Initial : AvatarUploadState

    object Success : AvatarUploadState

    object Loading : AvatarUploadState

    object Error : AvatarUploadState

}