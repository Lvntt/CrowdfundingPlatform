package com.example.crowdfundingplatform.presentation.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.crowdfundingplatform.R
import com.example.crowdfundingplatform.presentation.ui.common.IconTextButton
import com.example.crowdfundingplatform.presentation.ui.theme.OnboardingButtonWeight
import com.example.crowdfundingplatform.presentation.ui.theme.OnboardingIconSize
import com.example.crowdfundingplatform.presentation.ui.theme.OnboardingIconSpacing
import com.example.crowdfundingplatform.presentation.ui.theme.OnboardingTitleWeight
import com.example.crowdfundingplatform.presentation.ui.theme.Title

@Composable
fun OnboardingScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier.weight(OnboardingTitleWeight),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.logo_stub),
                contentDescription = "",
                tint = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.size(OnboardingIconSize)
            )
            Spacer(modifier = Modifier.height(OnboardingIconSpacing))
            Text(
                text = stringResource(id = R.string.app_name),
                color = MaterialTheme.colorScheme.onBackground,
                style = Title
            )
        }
        Column(
            modifier = Modifier.weight(OnboardingButtonWeight),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            IconTextButton(
                text = stringResource(id = R.string.onboarding_button),
                icon = painterResource(id = R.drawable.double_arrow_right)
            )
        }
    }
}

