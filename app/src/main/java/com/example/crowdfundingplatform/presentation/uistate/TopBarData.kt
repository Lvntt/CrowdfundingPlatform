package com.example.crowdfundingplatform.presentation.uistate

import androidx.annotation.StringRes
import androidx.navigation.NavHostController

data class TopBarData(
    @StringRes val titleId: Int,
    val canNavigateUp: Boolean,
    val onNavigateUp: (NavHostController) -> Unit
)
