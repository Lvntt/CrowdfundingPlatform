package com.example.crowdfundingplatform.presentation.ui.navigation.graph

import androidx.activity.compose.BackHandler
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.crowdfundingplatform.R
import com.example.crowdfundingplatform.presentation.ui.common.topbar.CreateProjectTopAppBar
import com.example.crowdfundingplatform.presentation.ui.common.topbar.CrowdfundingTopAppBar
import com.example.crowdfundingplatform.presentation.ui.common.topbar.DashboardTopAppBar
import com.example.crowdfundingplatform.presentation.ui.screen.dashboard.DashboardScreen
import com.example.crowdfundingplatform.presentation.ui.screen.profile.edit.EditEmailScreen
import com.example.crowdfundingplatform.presentation.ui.screen.profile.edit.EditPasswordScreen
import com.example.crowdfundingplatform.presentation.ui.screen.profile.edit.EditPersonalInfoScreen
import com.example.crowdfundingplatform.presentation.ui.screen.profile.ProfileInfoScreen
import com.example.crowdfundingplatform.presentation.ui.screen.profile.balance.PaymentScreen
import com.example.crowdfundingplatform.presentation.ui.screen.creation.ProjectCreationScreen
import com.example.crowdfundingplatform.presentation.uistate.CrowdfundingAppState

object MainGraphDestinations {
    const val PROFILE = "profile"
    const val DASHBOARD = "dashboard"
    const val EDIT_EMAIL = "edit_email"
    const val EDIT_PASSWORD = "edit_password"
    const val EDIT_PERSONAL_INFO = "edit_personal_info"
    const val CREATE_PROJECT = "create_project"
    const val BALANCE = "balance"
}

@OptIn(ExperimentalMaterial3Api::class)
object MainGraphTopBarInfo {
    val TOP_BAR_DESTINATIONS = arrayOf(
        MainGraphDestinations.DASHBOARD,
        MainGraphDestinations.EDIT_EMAIL,
        MainGraphDestinations.EDIT_PASSWORD,
        MainGraphDestinations.EDIT_PERSONAL_INFO,
        MainGraphDestinations.CREATE_PROJECT,
        MainGraphDestinations.BALANCE
    )
    val TOP_BAR_DATA = mapOf<String, @Composable (NavHostController) -> Unit>(
        MainGraphDestinations.DASHBOARD to @Composable {
            DashboardTopAppBar()
        },
        MainGraphDestinations.EDIT_EMAIL to @Composable { navController ->
            CrowdfundingTopAppBar(
                title = stringResource(id = R.string.editEmail),
                canNavigateBack = true,
                onNavigateUp = { navController.navigate(MainGraphDestinations.PROFILE) }
            )
        },
        MainGraphDestinations.EDIT_PASSWORD to @Composable { navController ->
            CrowdfundingTopAppBar(
                title = stringResource(id = R.string.editPassword),
                canNavigateBack = true,
                onNavigateUp = { navController.navigate(MainGraphDestinations.PROFILE) }
            )
        },
        MainGraphDestinations.EDIT_PERSONAL_INFO to @Composable { navController ->
            CrowdfundingTopAppBar(
                title = stringResource(id = R.string.editPersonalInfo),
                canNavigateBack = true,
                onNavigateUp = { navController.navigate(MainGraphDestinations.PROFILE) }
            )
        },
        MainGraphDestinations.CREATE_PROJECT to @Composable {
            CreateProjectTopAppBar()
        },
        MainGraphDestinations.BALANCE to @Composable { navController ->
            CrowdfundingTopAppBar(
                title = stringResource(id = R.string.balance),
                canNavigateBack = true,
                onNavigateUp = { navController.navigate(MainGraphDestinations.PROFILE) }
            )
        }
    )
}

fun NavGraphBuilder.mainNavGraph(appState: CrowdfundingAppState) {
    navigation(
        route = CrowdfundingGraphs.MAIN, startDestination = MainGraphDestinations.DASHBOARD
    ) {
        composable(MainGraphDestinations.PROFILE) {
            ProfileInfoScreen(
                onEditEmailClick = { appState.navController.navigate(MainGraphDestinations.EDIT_EMAIL) },
                onEditPasswordClick = { appState.navController.navigate(MainGraphDestinations.EDIT_PASSWORD) },
                onEditPersonalInfoClick = { appState.navController.navigate(MainGraphDestinations.EDIT_PERSONAL_INFO) },
                onCreateProjectClick = { appState.navController.navigate(MainGraphDestinations.CREATE_PROJECT) },
                onAddBalanceClick = { appState.navController.navigate(MainGraphDestinations.BALANCE) },
                onSignOut = { appState.navController.navigate(AuthGraphDestinations.AUTHORIZATION_ROUTE) }
            )
        }
        composable(MainGraphDestinations.DASHBOARD) {
            DashboardScreen()
        }
        composable(MainGraphDestinations.EDIT_EMAIL) {
            EditEmailScreen()
            BackHandler {
                appState.navController.navigate(MainGraphDestinations.PROFILE)
            }
        }
        composable(MainGraphDestinations.EDIT_PASSWORD) {
            EditPasswordScreen()
            BackHandler {
                appState.navController.navigate(MainGraphDestinations.PROFILE)
            }
        }
        composable(MainGraphDestinations.EDIT_PERSONAL_INFO) {
            EditPersonalInfoScreen(
                onSignedOut = { appState.navController.navigate(AuthGraphDestinations.AUTHORIZATION_ROUTE) },
                onSuccess = { appState.navController.navigate(MainGraphDestinations.PROFILE) }
            )
            BackHandler {
                appState.navController.navigate(MainGraphDestinations.PROFILE)
            }
        }
        composable(MainGraphDestinations.CREATE_PROJECT) {
            ProjectCreationScreen(
                onProjectCreationSuccess = {
                    appState.navController.navigate(MainGraphDestinations.PROFILE)
                }
            )
            BackHandler {
                appState.navController.navigate(MainGraphDestinations.PROFILE)
            }
        }
        composable(MainGraphDestinations.BALANCE) {
            PaymentScreen(
                onNavigateUp = {
                    appState.navController.navigate(MainGraphDestinations.PROFILE)
                }
            )
            BackHandler {
                appState.navController.navigate(MainGraphDestinations.PROFILE)
            }
        }
    }
}