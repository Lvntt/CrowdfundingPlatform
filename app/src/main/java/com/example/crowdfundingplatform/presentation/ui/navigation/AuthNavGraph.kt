package com.example.crowdfundingplatform.presentation.ui.navigation

import androidx.activity.compose.BackHandler
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.crowdfundingplatform.R
import com.example.crowdfundingplatform.presentation.ui.screen.LoginScreen
import com.example.crowdfundingplatform.presentation.ui.screen.OnboardingScreen
import com.example.crowdfundingplatform.presentation.ui.screen.RegistrationCredentialsScreen
import com.example.crowdfundingplatform.presentation.ui.screen.RegistrationPersonalInfoScreen
import com.example.crowdfundingplatform.presentation.uistate.AuthUiState
import com.example.crowdfundingplatform.presentation.uistate.CrowdfundingAppState
import com.example.crowdfundingplatform.presentation.uistate.TopBarData
import com.example.crowdfundingplatform.presentation.viewmodel.AuthViewModel

object AuthGraphDestinations {
    const val ONBOARDING_ROUTE = "onboarding"
    const val REGISTRATION_CREDENTIALS_ROUTE = "registration_credentials"
    const val REGISTRATION_PERSONAL_INFO_ROUTE = "registration_presonal_info"
    const val LOGIN_ROUTE = "login"
}

object AuthGraphTopBarInfo {
    val TOP_BAR_DESTINATIONS = arrayOf(
        AuthGraphDestinations.REGISTRATION_CREDENTIALS_ROUTE,
        AuthGraphDestinations.REGISTRATION_PERSONAL_INFO_ROUTE,
        AuthGraphDestinations.LOGIN_ROUTE
    )
    val TOP_BAR_DATA = mapOf(
        AuthGraphDestinations.REGISTRATION_CREDENTIALS_ROUTE to TopBarData(titleId = R.string.registrationTitle,
            canNavigateUp = true,
            onNavigateUp = { it.navigate(AuthGraphDestinations.LOGIN_ROUTE) }),
        AuthGraphDestinations.REGISTRATION_PERSONAL_INFO_ROUTE to TopBarData(titleId = R.string.registrationTitle,
            canNavigateUp = true,
            onNavigateUp = { it.navigate(AuthGraphDestinations.LOGIN_ROUTE) }),
        AuthGraphDestinations.LOGIN_ROUTE to TopBarData(titleId = R.string.signIn,
            canNavigateUp = true,
            onNavigateUp = { it.navigate(AuthGraphDestinations.ONBOARDING_ROUTE) })
    )
}

fun NavGraphBuilder.authNavGraph(appState: CrowdfundingAppState, viewModel: AuthViewModel) {
    navigation(
        route = CrowdfundingGraphs.AUTH, startDestination = AuthGraphDestinations.ONBOARDING_ROUTE
    ) {
        composable(AuthGraphDestinations.ONBOARDING_ROUTE) {
            OnboardingScreen(authViewModel = viewModel) {
                if (viewModel.authState.value == AuthUiState.Input) {
                    appState.navController.navigate(AuthGraphDestinations.LOGIN_ROUTE)
                }
            }
        }
        composable(AuthGraphDestinations.LOGIN_ROUTE) {
            LoginScreen(authViewModel = viewModel,
                onSignUpClick = { appState.navController.navigate(AuthGraphDestinations.REGISTRATION_PERSONAL_INFO_ROUTE) })
            BackHandler {
                appState.navController.navigate(AuthGraphDestinations.ONBOARDING_ROUTE)
            }
        }
        composable(AuthGraphDestinations.REGISTRATION_PERSONAL_INFO_ROUTE) {
            RegistrationPersonalInfoScreen(authViewModel = viewModel,
                onContinueClick = { appState.navController.navigate(AuthGraphDestinations.REGISTRATION_CREDENTIALS_ROUTE) })
            BackHandler {
                appState.navController.navigate(AuthGraphDestinations.LOGIN_ROUTE)
            }
        }
        composable(AuthGraphDestinations.REGISTRATION_CREDENTIALS_ROUTE) {
            RegistrationCredentialsScreen(
                authViewModel = viewModel
            )
            BackHandler {
                appState.navController.navigate(AuthGraphDestinations.LOGIN_ROUTE)
            }
        }
    }
}