package com.example.crowdfundingplatform.presentation.ui.common.topbar

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.crowdfundingplatform.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateProjectTopAppBar() {
    CrowdfundingTopAppBar(
        title = stringResource(id = R.string.createProject),
        canNavigateBack = false
    )
}