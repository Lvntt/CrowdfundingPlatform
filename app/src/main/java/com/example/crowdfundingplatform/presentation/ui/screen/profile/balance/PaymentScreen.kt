package com.example.crowdfundingplatform.presentation.ui.screen.profile.balance

import androidx.compose.animation.Crossfade
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
import com.example.crowdfundingplatform.presentation.uistate.payment.PaymentState
import com.example.crowdfundingplatform.presentation.viewmodel.PaymentViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun PaymentScreen(
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    paymentViewModel: PaymentViewModel = koinViewModel()
) {
    val paymentState by remember { paymentViewModel.paymentState }

    var shouldShowErrorDialog by remember { mutableStateOf(false) }
    var errorDialogMessageId by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        paymentViewModel.errorMessageFlow.collect { messageId ->
            shouldShowErrorDialog = true
            errorDialogMessageId = messageId
        }
    }

    Crossfade(targetState = paymentState) { state ->
        when (state) {
            PaymentState.Input -> {
                PaymentContent(
                    modifier = modifier,
                    paymentViewModel = paymentViewModel
                )
            }
            PaymentState.Loading -> {
                LoadingProgress()
            }
            PaymentState.Success -> {
                var shouldShowSuccessDialog by remember { mutableStateOf(true) }

                if (shouldShowSuccessDialog) {
                    TextAlertDialog(
                        title = stringResource(id = R.string.success),
                        text = stringResource(id = R.string.promoCodeSuccessfullyActivated),
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
                        onNavigateUp()
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