package com.example.crowdfundingplatform.presentation.uistate

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.crowdfundingplatform.presentation.ui.navigation.bottomnav.BottomNavItems
import com.example.crowdfundingplatform.presentation.ui.navigation.graph.CrowdfundingTopBarInfo

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
    val shouldShowBottomBar: Boolean
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination?.route in BottomNavItems.bottomNavItems.map { it.route }

    val shouldShowTopBar: Boolean
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination?.route in CrowdfundingTopBarInfo.TOP_BAR_DESTINATIONS

    val topBarData: @Composable (NavHostController) -> Unit
        get() {
            val topBarData: (@Composable (NavHostController) -> Unit)? = CrowdfundingTopBarInfo.TOP_BAR_DATA[navController.currentDestination?.route]
            if (topBarData != null) {
                prevTopBarData = topBarData
            }
            return topBarData ?: prevTopBarData
        }
    private var prevTopBarData: @Composable (NavHostController) -> Unit = {}

}