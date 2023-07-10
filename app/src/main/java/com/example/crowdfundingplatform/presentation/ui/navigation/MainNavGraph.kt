package com.example.crowdfundingplatform.presentation.ui.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.crowdfundingplatform.R
import com.example.crowdfundingplatform.data.mock.MockProjectSource
import com.example.crowdfundingplatform.presentation.ui.common.CrowdfundingTopAppBar
import com.example.crowdfundingplatform.presentation.ui.common.DashboardTopAppBar
import com.example.crowdfundingplatform.presentation.ui.screen.DashboardScreen
import com.example.crowdfundingplatform.presentation.ui.screen.EditEmailScreen
import com.example.crowdfundingplatform.presentation.ui.screen.EditPasswordScreen
import com.example.crowdfundingplatform.presentation.ui.screen.EditPersonalInfoScreen
import com.example.crowdfundingplatform.presentation.ui.screen.ProfileInfoScreen
import com.example.crowdfundingplatform.presentation.uistate.CrowdfundingAppState

object MainGraphDestinations {
    const val PROFILE = "profile"
    const val DASHBOARD = "dashboard"
    const val EDIT_EMAIL = "edit_email"
    const val EDIT_PASSWORD = "edit_password"
    const val EDIT_PERSONAL_INFO = "edit_personal_info"
}

@OptIn(ExperimentalMaterial3Api::class)
object MainGraphTopBarInfo {
    val TOP_BAR_DESTINATIONS = arrayOf(
        MainGraphDestinations.DASHBOARD,
        MainGraphDestinations.EDIT_EMAIL,
        MainGraphDestinations.EDIT_PASSWORD,
        MainGraphDestinations.EDIT_PERSONAL_INFO
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
        }
    )
}

fun NavGraphBuilder.mainNavGraph(appState: CrowdfundingAppState) {
    navigation(
        route = CrowdfundingGraphs.MAIN, startDestination = MainGraphDestinations.PROFILE
    ) {
        composable(MainGraphDestinations.PROFILE) {
            ProfileInfoScreen(
                email = "cat@xddd.com",
                name = "Cat",
                surname = "Based",
                patronymic = "Super-based",
                photoId = R.drawable.cat_image,
                onEditEmailClick = { appState.navController.navigate(MainGraphDestinations.EDIT_EMAIL) },
                onEditPasswordClick = { appState.navController.navigate(MainGraphDestinations.EDIT_PASSWORD) },
                onEditPersonalInfoClick = { appState.navController.navigate(MainGraphDestinations.EDIT_PERSONAL_INFO) }
            )
        }
        composable(MainGraphDestinations.DASHBOARD) {
            DashboardScreen(projects = MockProjectSource.projects)
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
                name = "Cat",
                surname = "Based",
                patronymic = "Super-based"
            )
            BackHandler {
                appState.navController.navigate(MainGraphDestinations.PROFILE)
            }
        }
    }
}