package com.example.crowdfundingplatform.presentation.ui.navigation.graph

import android.app.Activity
import android.content.Context
import androidx.activity.compose.BackHandler
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.crowdfundingplatform.R
import com.example.crowdfundingplatform.presentation.ui.common.topbar.CrowdfundingTopAppBar
import com.example.crowdfundingplatform.presentation.ui.screen.auth.AuthScreen
import com.example.crowdfundingplatform.presentation.ui.screen.onboarding.OnboardingScreen
import com.example.crowdfundingplatform.presentation.uistate.CrowdfundingAppState

object AuthGraphDestinations {
    const val ONBOARDING_ROUTE = "onboarding"
    const val AUTHORIZATION_ROUTE = "authorization"
}

@OptIn(ExperimentalMaterial3Api::class)
object AuthGraphTopBarInfo {
    val TOP_BAR_DESTINATIONS = arrayOf(
        AuthGraphDestinations.AUTHORIZATION_ROUTE
    )
    val TOP_BAR_DATA =
        mapOf<String, @Composable (NavHostController) -> Unit>(AuthGraphDestinations.AUTHORIZATION_ROUTE to @Composable { navController ->
            CrowdfundingTopAppBar(title = stringResource(id = R.string.authTitle),
                canNavigateBack = true,
                onNavigateUp = { navController.navigate(AuthGraphDestinations.ONBOARDING_ROUTE) })
        })
}

fun NavGraphBuilder.authNavGraph(appState: CrowdfundingAppState, context: Context) {
    navigation(
        route = CrowdfundingGraphs.AUTH, startDestination = AuthGraphDestinations.ONBOARDING_ROUTE
    ) {
        composable(route = AuthGraphDestinations.ONBOARDING_ROUTE) {
            OnboardingScreen(onContinueClick = {
                appState.navController.navigate(
                    AuthGraphDestinations.AUTHORIZATION_ROUTE
                )
            }, onLoginCheckPassed = { appState.navController.navigate(CrowdfundingGraphs.MAIN) })
        }
        composable(route = AuthGraphDestinations.AUTHORIZATION_ROUTE) {
            AuthScreen(onSignInSuccess = {
                appState.navController.navigate(CrowdfundingGraphs.MAIN)
            })
            BackHandler {
                val activity = (context as? Activity)
                activity?.finishAffinity()
            }
        }
    }
}