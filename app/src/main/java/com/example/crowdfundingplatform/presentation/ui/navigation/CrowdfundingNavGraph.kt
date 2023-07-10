package com.example.crowdfundingplatform.presentation.ui.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.crowdfundingplatform.R
import com.example.crowdfundingplatform.presentation.ui.screen.EditEmailScreen
import com.example.crowdfundingplatform.presentation.ui.screen.EditPasswordScreen
import com.example.crowdfundingplatform.presentation.ui.screen.EditPersonalInfoScreen
import com.example.crowdfundingplatform.presentation.ui.screen.LoginScreen
import com.example.crowdfundingplatform.presentation.ui.screen.OnboardingScreen
import com.example.crowdfundingplatform.presentation.ui.screen.ProfileInfoScreen
import com.example.crowdfundingplatform.presentation.ui.screen.RegistrationCredentialsScreen
import com.example.crowdfundingplatform.presentation.ui.screen.RegistrationPersonalInfoScreen

object CrowdfundingDestinations {
    const val ONBOARDING_ROUTE = "onboarding"
    const val REGISTRATION_CREDENTIALS_ROUTE = "registration_credentials"
    const val REGISTRATION_PERSONAL_INFO_ROUTE = "registration_presonal_info"
    const val LOGIN_ROUTE = "login"
    const val PROFILE_INFO_ROUTE = "profile_info"
    const val EDIT_EMAIL_ROUTE = "edit_email"
    const val EDIT_PASSWORD_ROUTE = "edit_password"
    const val EDIT_PERSONAL_INFO_ROUTE = "edit_personal_info"
}

@Composable
fun Navigation(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = CrowdfundingDestinations.ONBOARDING_ROUTE
    ) {
        composable(CrowdfundingDestinations.ONBOARDING_ROUTE) {
            OnboardingScreen(
                onContinueClick = { navController.navigate(CrowdfundingDestinations.LOGIN_ROUTE) }
            )
        }
        composable(CrowdfundingDestinations.LOGIN_ROUTE) {
            LoginScreen(
                onSignInClick = { navController.navigate(CrowdfundingDestinations.PROFILE_INFO_ROUTE) },
                onSignUpClick = { navController.navigate(CrowdfundingDestinations.REGISTRATION_PERSONAL_INFO_ROUTE) },
                onNavigateUp = { navController.navigate(CrowdfundingDestinations.ONBOARDING_ROUTE) }
            )
            BackHandler {
                navController.navigate(CrowdfundingDestinations.ONBOARDING_ROUTE)
            }
        }
        composable(CrowdfundingDestinations.REGISTRATION_PERSONAL_INFO_ROUTE) {
            RegistrationPersonalInfoScreen(
                onContinueClick = { navController.navigate(CrowdfundingDestinations.REGISTRATION_CREDENTIALS_ROUTE) },
                onNavigateUp = {
                    navController.navigate(CrowdfundingDestinations.LOGIN_ROUTE)
                }
            )
            BackHandler {
                navController.navigate(CrowdfundingDestinations.LOGIN_ROUTE)
            }
        }
        composable(CrowdfundingDestinations.REGISTRATION_CREDENTIALS_ROUTE) {
            RegistrationCredentialsScreen(
                onSignUpClick = { navController.navigate(CrowdfundingDestinations.PROFILE_INFO_ROUTE) },
                onNavigateUp = { navController.navigate(CrowdfundingDestinations.LOGIN_ROUTE) }
            )
            BackHandler {
                navController.navigate(CrowdfundingDestinations.LOGIN_ROUTE)
            }
        }
        composable(CrowdfundingDestinations.PROFILE_INFO_ROUTE) {
            ProfileInfoScreen(
                email = "cat@xddd.com",
                name = "Cat",
                surname = "Based",
                patronymic = "Super-based",
                photoId = R.drawable.cat_image,
                onEditEmailClick = { navController.navigate(CrowdfundingDestinations.EDIT_EMAIL_ROUTE) },
                onEditPasswordClick = { navController.navigate(CrowdfundingDestinations.EDIT_PASSWORD_ROUTE) },
                onEditPersonalInfoClick = { navController.navigate(CrowdfundingDestinations.EDIT_PERSONAL_INFO_ROUTE) }
            )
        }
        composable(CrowdfundingDestinations.EDIT_EMAIL_ROUTE) {
            EditEmailScreen(
                onNavigateUp = { navController.navigate(CrowdfundingDestinations.PROFILE_INFO_ROUTE) }
            )
            BackHandler {
                navController.navigate(CrowdfundingDestinations.PROFILE_INFO_ROUTE)
            }
        }
        composable(CrowdfundingDestinations.EDIT_PASSWORD_ROUTE) {
            EditPasswordScreen(
                onNavigateUp = { navController.navigate(CrowdfundingDestinations.PROFILE_INFO_ROUTE) }
            )
            BackHandler {
                navController.navigate(CrowdfundingDestinations.PROFILE_INFO_ROUTE)
            }
        }
        composable(CrowdfundingDestinations.EDIT_PERSONAL_INFO_ROUTE) {
            EditPersonalInfoScreen(
                name = "Cat",
                surname = "Based",
                patronymic = "Super-based",
                onNavigateUp = { navController.navigate(CrowdfundingDestinations.PROFILE_INFO_ROUTE) }
            )
            BackHandler {
                navController.navigate(CrowdfundingDestinations.PROFILE_INFO_ROUTE)
            }
        }
    }
}