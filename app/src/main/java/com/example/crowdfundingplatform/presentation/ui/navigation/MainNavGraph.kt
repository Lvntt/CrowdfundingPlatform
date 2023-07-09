package com.example.crowdfundingplatform.presentation.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.crowdfundingplatform.presentation.ui.screen.ProfileInfoScreen
import com.example.crowdfundingplatform.presentation.uistate.CrowdfundingAppState

object MainGraphDestinations {
    const val PROFILE = "profile"
}

fun NavGraphBuilder.mainNavGraph(appState: CrowdfundingAppState) {
    navigation(
        route = CrowdfundingGraphs.MAIN, startDestination = MainGraphDestinations.PROFILE
    ) {
        //TODO implement other screens (dashboard, etc.) with bottom bar
        composable(MainGraphDestinations.PROFILE) {
            ProfileInfoScreen(
                name = "Placeholder name",
                email = "placeholder@xddddd.com"
            )
        }
    }
}