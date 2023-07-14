package com.example.crowdfundingplatform.presentation.ui.screen.creation

import androidx.activity.compose.BackHandler
import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.example.crowdfundingplatform.R
import com.example.crowdfundingplatform.presentation.ui.common.LoadingProgress
import com.example.crowdfundingplatform.presentation.ui.common.TextAlertDialog
import com.example.crowdfundingplatform.presentation.uistate.creation.CreationType
import com.example.crowdfundingplatform.presentation.uistate.creation.ProjectCreationState
import com.example.crowdfundingplatform.presentation.viewmodel.ProjectCreationViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProjectCreationScreen(
    onProjectCreationSuccess: () -> Unit,
    projectCreationViewModel: ProjectCreationViewModel = koinViewModel()
) {
    val creationType by remember { projectCreationViewModel.creationType }
    val creationState by remember { projectCreationViewModel.creationState }

    var shouldShowErrorDialog by remember { mutableStateOf(false) }
    var errorDialogMessageId by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        projectCreationViewModel.errorMessageFlow.collect { messageId ->
            shouldShowErrorDialog = true
            errorDialogMessageId = messageId
        }
    }

    Crossfade(targetState = creationState) { state ->
        when (state) {
            ProjectCreationState.Input -> {
                CreationBody(
                    projectCreationViewModel = projectCreationViewModel,
                    creationType = creationType
                )
            }
            ProjectCreationState.Loading -> {
                LoadingProgress()
            }
            ProjectCreationState.Success -> {
                var shouldShowSuccessDialog by remember { mutableStateOf(true) }

                if (shouldShowSuccessDialog) {
                    TextAlertDialog(
                        title = stringResource(id = R.string.success),
                        text = stringResource(id = R.string.projectCreationInfo),
                        confirmText = stringResource(id = R.string.ok),
                        onConfirm = {
                            shouldShowSuccessDialog = false
                        },
                        onDismiss = {
                            shouldShowSuccessDialog = false
                        }
                    )
                } else {
                    LaunchedEffect(Unit) {
                        onProjectCreationSuccess()
                    }
                }
            }
        }
    }

    if (shouldShowErrorDialog) {
        TextAlertDialog(
            title = stringResource(id = R.string.error),
            text = stringResource(id = errorDialogMessageId),
            confirmText = stringResource(id = R.string.ok),
            onConfirm = {
                shouldShowErrorDialog = false
            },
            onDismiss = {
                shouldShowErrorDialog = false
            }
        )
    }
}

@Composable
fun CreationBody(
    projectCreationViewModel: ProjectCreationViewModel,
    creationType: CreationType
) {
    Crossfade(targetState = creationType) { type ->
        when (type) {
            CreationType.SUMMARY -> {
                SummaryContent(projectCreationViewModel = projectCreationViewModel)
            }
            CreationType.AVATAR -> {
                AvatarContent(projectCreationViewModel = projectCreationViewModel)
                BackHandler {
                    projectCreationViewModel.setCreationType(CreationType.SUMMARY)
                }
            }
            CreationType.CATEGORY -> {
                CategoryContent(projectCreationViewModel = projectCreationViewModel)
                BackHandler {
                    projectCreationViewModel.setCreationType(CreationType.AVATAR)
                }
            }
            CreationType.DESCRIPTION -> {
                DescriptionContent(projectCreationViewModel = projectCreationViewModel)
                BackHandler {
                    projectCreationViewModel.setCreationType(CreationType.CATEGORY)
                }
            }
        }
    }
}