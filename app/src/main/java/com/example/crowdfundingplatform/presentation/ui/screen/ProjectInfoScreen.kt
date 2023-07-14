package com.example.crowdfundingplatform.presentation.ui.screen

import androidx.compose.runtime.Composable
import com.example.crowdfundingplatform.presentation.viewmodel.ProjectInfoViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun ProjectInfoScreen(
    projectId: String,
    viewModel: ProjectInfoViewModel = koinViewModel { parametersOf(projectId) }
) {
}