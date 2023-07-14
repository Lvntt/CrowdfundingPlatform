package com.example.crowdfundingplatform.presentation.ui.extension

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.navigation.NavDestination

fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier = composed {
    clickable(indication = null, interactionSource = remember { MutableInteractionSource() }) {
        onClick()
    }
}

fun NavDestination.routeWithoutArguments(): String? {
    return if (route?.contains("/") == true) {
        val argumentSeparatorIndex = route?.indexOf('/')
        if (argumentSeparatorIndex != null && argumentSeparatorIndex != -1) route?.substring(
            0, argumentSeparatorIndex
        ) else null
    } else {
        route
    }
}