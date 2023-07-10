package com.example.crowdfundingplatform.presentation.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.crowdfundingplatform.presentation.ui.common.LoadingProgress
import com.example.crowdfundingplatform.presentation.ui.common.TextAlertDialog
import com.example.crowdfundingplatform.presentation.ui.navigation.CrowdfundingNavigation
import com.example.crowdfundingplatform.presentation.ui.theme.CrowdfundingPlatformTheme
import com.example.crowdfundingplatform.presentation.uistate.rememberCrowdfundingAppState
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CrowdfundingApp() {
    CrowdfundingPlatformTheme {
        val appState = rememberCrowdfundingAppState()
        val uiController = rememberSystemUiController()
        uiController.setSystemBarsColor(MaterialTheme.colorScheme.background)
        Scaffold(topBar = {
            AnimatedVisibility(
                visible = appState.shouldShowTopBar,
                enter = fadeIn() + expandVertically(),
                exit = shrinkVertically() + fadeOut()
            ) {
                appState.topBarData(appState.navController)
            }
        }, bottomBar = {
            if (appState.shouldShowBottomBar) {
                //TODO add bottom bar
            }
        }) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                if (appState.shouldShowLoadingProgress) {
                    LoadingProgress()
                }
                if (appState.shouldShowAlertDialog) {
                    val dismissText = appState.alertDialogData.dismissText
                    TextAlertDialog(
                        title = stringResource(id = appState.alertDialogData.title),
                        text = stringResource(id = appState.alertDialogData.text),
                        confirmText = stringResource(id = appState.alertDialogData.confirmText),
                        dismissText = if (dismissText != null) stringResource(
                            id = dismissText
                        ) else null,
                        onConfirm = appState.alertDialogData.onConfirm,
                        onDismiss = appState.alertDialogData.onDismiss
                    )
                }
                CrowdfundingNavigation(appState)
            }
        }
    }
}