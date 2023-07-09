package com.example.crowdfundingplatform.presentation.uistate

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.crowdfundingplatform.R
import com.example.crowdfundingplatform.presentation.ui.navigation.CrowdfundingGraphs
import com.example.crowdfundingplatform.presentation.ui.navigation.CrowdfundingTopBarInfo

@Composable
fun rememberCrowdfundingAppState(
    navController: NavHostController = rememberNavController()
) = remember(navController) {
    CrowdfundingAppState(navController)
}

@Stable
class CrowdfundingAppState(
    val navController: NavHostController
) {
    //TODO add bottom bar routes
    val shouldShowBottomBar: Boolean
        @Composable get() = false

    val shouldShowTopBar: Boolean
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination?.route in CrowdfundingTopBarInfo.TOP_BAR_DESTINATIONS

    val shouldShowLoadingProgress: Boolean
        @Composable get() = _shouldShowLoadingProgress.value
    private var _shouldShowLoadingProgress = mutableStateOf(false)

    val shouldShowAlertDialog: Boolean
        @Composable get() =_shouldShowAlertDialog.value
    private var _shouldShowAlertDialog = mutableStateOf(false)

    val alertDialogData: AlertDialogData
        get() = _alertDialogData
    private var _alertDialogData = AlertDialogData(0, 0, 0, null, {}, {})

    val topBarData: TopBarData
        get() {
            val topBarData = CrowdfundingTopBarInfo.TOP_BAR_DATA[navController.currentDestination?.route]
            if (topBarData != null) {
                prevTopBarData = topBarData
            }
            return topBarData ?: prevTopBarData
        }
    private var prevTopBarData = TopBarData(0, false) {}

    fun showAlertDialog(dialogData: AlertDialogData) {
        if (_shouldShowAlertDialog.value) {
            _alertDialogData.onDismiss()
        }
        _alertDialogData = dialogData.copy(onDismiss = {
            dialogData.onDismiss()
            _shouldShowAlertDialog.value = false
        })
        _shouldShowAlertDialog.value = true
    }

    fun showErrorDialog(@StringRes errorMessage: Int) {
        if (_shouldShowAlertDialog.value) {
            _alertDialogData.onDismiss()
        }
        val onDismiss = { _shouldShowAlertDialog.value = false }
        _alertDialogData = AlertDialogData(
            title = R.string.error,
            text = errorMessage,
            confirmText = R.string.ok,
            dismissText = null,
            onConfirm = onDismiss,
            onDismiss = onDismiss
        )
        _shouldShowAlertDialog.value = true
    }

    fun showLoadingProgress() {
        _shouldShowLoadingProgress.value = true
    }

    fun hideLoadingProgress() {
        _shouldShowLoadingProgress.value = false
    }

    fun navigateToHome() {
        navController.navigate(CrowdfundingGraphs.MAIN)
    }

}