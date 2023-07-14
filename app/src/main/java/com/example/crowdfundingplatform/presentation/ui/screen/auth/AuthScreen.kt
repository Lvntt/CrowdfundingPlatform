package com.example.crowdfundingplatform.presentation.ui.screen.auth

import androidx.activity.compose.BackHandler
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.crowdfundingplatform.R
import com.example.crowdfundingplatform.presentation.ui.common.state.LoadingProgress
import com.example.crowdfundingplatform.presentation.ui.common.dialog.TextAlertDialog
import com.example.crowdfundingplatform.presentation.ui.screen.auth.login.LoginContent
import com.example.crowdfundingplatform.presentation.ui.screen.auth.registration.RegistrationCredentialsContent
import com.example.crowdfundingplatform.presentation.ui.screen.auth.registration.RegistrationPersonalInfoContent
import com.example.crowdfundingplatform.presentation.uistate.auth.AuthType
import com.example.crowdfundingplatform.presentation.uistate.auth.AuthState
import com.example.crowdfundingplatform.presentation.viewmodel.AuthViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AuthScreen(
    onSignInSuccess: () -> Unit
) {
    val authViewModel: AuthViewModel = koinViewModel()
    val authState by remember { authViewModel.authState }
    val authType by remember { authViewModel.authType }
    var shouldShowErrorDialog by remember { mutableStateOf(false) }
    var errorDialogMessageId by remember { mutableStateOf(0) }
    LaunchedEffect(Unit) {
        authViewModel.errorMessageFlow.collect { messageId ->
            shouldShowErrorDialog = true
            errorDialogMessageId = messageId
        }
    }
    Crossfade(targetState = authState, label = "") { state ->
        when (state) {
            AuthState.Loading -> {
                LoadingProgress()
            }

            AuthState.Success -> {
                LaunchedEffect(Unit) {
                    onSignInSuccess()
                }
            }

            AuthState.Input -> {
                AuthContent(authType, authViewModel)
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
            })
    }
}

@Composable
private fun AuthContent(
    authType: AuthType,
    authViewModel: AuthViewModel
) {
    Column(
        modifier = Modifier.verticalScroll(
            rememberScrollState()
        )
    ) {
        Crossfade(targetState = authType, label = "") { type ->
            when (type) {
                AuthType.LOG_IN -> {
                    LoginContent(
                        authViewModel = authViewModel
                    )
                }

                AuthType.REGISTER_PERSONAL_INFO -> {
                    BackHandler (onBack = authViewModel::openLogin)
                    RegistrationPersonalInfoContent(
                        authViewModel = authViewModel
                    )
                }

                AuthType.REGISTER_CREDENTIALS -> {
                    BackHandler (onBack = authViewModel::openRegistrationPersonalInfo)
                    RegistrationCredentialsContent(authViewModel = authViewModel)
                }
            }
        }
    }
}