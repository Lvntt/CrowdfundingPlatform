package com.example.crowdfundingplatform.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.crowdfundingplatform.presentation.uistate.CrowdfundingAppState
import com.example.crowdfundingplatform.presentation.viewmodel.AuthViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

object CrowdfundingGraphs {
    const val AUTH = "auth"
    const val MAIN = "main"
}

object CrowdfundingTopBarInfo {
    val TOP_BAR_DESTINATIONS = AuthGraphTopBarInfo.TOP_BAR_DESTINATIONS
    val TOP_BAR_DATA: Map<String, @Composable (NavHostController) -> Unit> = AuthGraphTopBarInfo.TOP_BAR_DATA
}

@Composable
fun CrowdfundingNavigation(
    appState: CrowdfundingAppState
) {
    val authViewModel: AuthViewModel = koinViewModel {
        parametersOf(appState)
    }
    NavHost(
        navController = appState.navController,
        startDestination = CrowdfundingGraphs.AUTH) {
        authNavGraph(appState, authViewModel)
        mainNavGraph(appState)
    }
}