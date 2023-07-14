package com.example.crowdfundingplatform.presentation.ui.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.crowdfundingplatform.presentation.uistate.CrowdfundingAppState

object CrowdfundingGraphs {
    const val AUTH = "auth"
    const val MAIN = "main"
}

object CrowdfundingTopBarInfo {
    val TOP_BAR_DESTINATIONS = AuthGraphTopBarInfo.TOP_BAR_DESTINATIONS + MainGraphTopBarInfo.TOP_BAR_DESTINATIONS
    val TOP_BAR_DATA: Map<String, @Composable (NavHostController) -> Unit> = AuthGraphTopBarInfo.TOP_BAR_DATA + MainGraphTopBarInfo.TOP_BAR_DATA
}

@Composable
fun CrowdfundingNavigation(
    appState: CrowdfundingAppState
) {
    NavHost(
        navController = appState.navController,
        startDestination = CrowdfundingGraphs.AUTH
    ) {
        authNavGraph(appState)
        mainNavGraph(appState)
    }
}