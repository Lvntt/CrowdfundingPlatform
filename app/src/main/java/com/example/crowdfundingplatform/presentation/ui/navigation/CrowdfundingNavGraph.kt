package com.example.crowdfundingplatform.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.crowdfundingplatform.presentation.ui.screen.ProfileInfoScreen
import com.example.crowdfundingplatform.presentation.viewmodel.AuthViewModel
import org.koin.androidx.compose.koinViewModel

object CrowdfundingGraphs {
    const val AUTH = "auth"
    const val MAIN = "main"
}

@Composable
fun Navigation(
    navController: NavHostController
) {
    val authViewModel: AuthViewModel = koinViewModel()
    NavHost(
        navController = navController,
        startDestination = CrowdfundingGraphs.AUTH
    ) {
        authNavGraph(navController, authViewModel)
        composable(CrowdfundingGraphs.MAIN) {
            ProfileInfoScreen(
                name = "Placeholder name",
                email = "placeholder@xddddd.com"
            )
        }
    }
}