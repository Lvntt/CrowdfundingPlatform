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
import com.example.crowdfundingplatform.presentation.ui.navigation.bottomnav.BottomNavItems
import com.example.crowdfundingplatform.presentation.ui.navigation.bottomnav.BottomNavigationBar
import com.example.crowdfundingplatform.presentation.ui.navigation.graph.CrowdfundingNavigation
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
            AnimatedVisibility(
                visible = appState.shouldShowBottomBar,
                enter = fadeIn() + expandVertically(),
                exit = shrinkVertically() + fadeOut()
            ) {
                BottomNavigationBar(
                    buttons = BottomNavItems.bottomNavItems,
                    navController = appState.navController,
                    onItemClick = {
                        appState.navController.navigate(it.route)
                    }
                )
            }
        }) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                CrowdfundingNavigation(appState)
            }
        }
    }
}