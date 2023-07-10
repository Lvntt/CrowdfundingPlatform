package com.example.crowdfundingplatform.presentation.uistate

import androidx.annotation.StringRes

data class AlertDialogData(
    @StringRes val title: Int,
    @StringRes val text: Int,
    @StringRes val confirmText: Int,
    @StringRes val dismissText: Int?,
    val onConfirm: () -> Unit,
    val onDismiss: () -> Unit
)
