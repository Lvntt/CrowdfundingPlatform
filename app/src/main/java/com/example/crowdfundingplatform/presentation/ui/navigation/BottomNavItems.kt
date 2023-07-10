package com.example.crowdfundingplatform.presentation.ui.navigation

import com.example.crowdfundingplatform.R

object BottomNavItems {
    val bottomNavItems = listOf(
        BottomNavItem(
            name = R.string.dashboard,
            route = MainGraphDestinations.DASHBOARD,
            iconId = R.drawable.dashboard_icon
        ),
        BottomNavItem(
            name = R.string.profile,
            route = MainGraphDestinations.PROFILE,
            iconId = R.drawable.profile_icon
        )
    )
}