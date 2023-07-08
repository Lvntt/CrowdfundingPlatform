package com.example.crowdfundingplatform.presentation.ui.navigation

import androidx.activity.compose.BackHandler
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.crowdfundingplatform.presentation.ui.screen.LoginScreen
import com.example.crowdfundingplatform.presentation.ui.screen.OnboardingScreen
import com.example.crowdfundingplatform.presentation.ui.screen.RegistrationCredentialsScreen
import com.example.crowdfundingplatform.presentation.ui.screen.RegistrationPersonalInfoScreen
import com.example.crowdfundingplatform.presentation.uistate.AuthUiState
import com.example.crowdfundingplatform.presentation.viewmodel.AuthViewModel

object AuthGraphDestinations {
    const val ONBOARDING_ROUTE = "onboarding"
    const val REGISTRATION_CREDENTIALS_ROUTE = "registration_credentials"
    const val REGISTRATION_PERSONAL_INFO_ROUTE = "registration_presonal_info"
    const val LOGIN_ROUTE = "login"
}

fun NavGraphBuilder.authNavGraph(navController: NavHostController, viewModel: AuthViewModel) {
    navigation(
        route = CrowdfundingGraphs.AUTH, startDestination = AuthGraphDestinations.ONBOARDING_ROUTE
    ) {
        composable(AuthGraphDestinations.ONBOARDING_ROUTE) {
            OnboardingScreen(onContinueClick = {
                if (viewModel.authState.value == AuthUiState.Input) {
                    navController.navigate(AuthGraphDestinations.LOGIN_ROUTE)
                }
            }, authViewModel = viewModel, onSignedInCheckSuccess = {
                navController.navigate(CrowdfundingGraphs.MAIN)
            })
        }
        composable(AuthGraphDestinations.LOGIN_ROUTE) {
            LoginScreen(authViewModel = viewModel,
                onSignUpClick = { navController.navigate(AuthGraphDestinations.REGISTRATION_PERSONAL_INFO_ROUTE) },
                onNavigateUp = { navController.navigate(AuthGraphDestinations.ONBOARDING_ROUTE) },
                onSignInSuccess = { navController.navigate(CrowdfundingGraphs.MAIN) })
            BackHandler {
                navController.navigate(AuthGraphDestinations.ONBOARDING_ROUTE)
            }
        }
        composable(AuthGraphDestinations.REGISTRATION_PERSONAL_INFO_ROUTE) {
            RegistrationPersonalInfoScreen(authViewModel = viewModel,
                onContinueClick = { navController.navigate(AuthGraphDestinations.REGISTRATION_CREDENTIALS_ROUTE) },
                onNavigateUp = {
                    navController.navigate(AuthGraphDestinations.LOGIN_ROUTE)
                })
            BackHandler {
                navController.navigate(AuthGraphDestinations.LOGIN_ROUTE)
            }
        }
        composable(AuthGraphDestinations.REGISTRATION_CREDENTIALS_ROUTE) {
            RegistrationCredentialsScreen(authViewModel = viewModel,
                onNavigateUp = { navController.navigate(AuthGraphDestinations.LOGIN_ROUTE) },
                onSignUpSuccess = { navController.navigate(CrowdfundingGraphs.MAIN) })
            BackHandler {
                navController.navigate(AuthGraphDestinations.LOGIN_ROUTE)
            }
        }
    }
}