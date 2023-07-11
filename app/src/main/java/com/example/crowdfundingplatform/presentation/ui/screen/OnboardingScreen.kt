package com.example.crowdfundingplatform.presentation.ui.screen

import androidx.compose.animation.Crossfade
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.crowdfundingplatform.R
import com.example.crowdfundingplatform.presentation.ui.common.TextButton
import com.example.crowdfundingplatform.presentation.ui.theme.OnboardingButtonWeight
import com.example.crowdfundingplatform.presentation.ui.theme.OnboardingIconSize
import com.example.crowdfundingplatform.presentation.ui.theme.OnboardingIconSpacing
import com.example.crowdfundingplatform.presentation.ui.theme.OnboardingTitleWeight
import com.example.crowdfundingplatform.presentation.ui.theme.Title
import com.example.crowdfundingplatform.presentation.uistate.OnboardingState
import com.example.crowdfundingplatform.presentation.viewmodel.OnboardingViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun OnboardingScreen(
    onContinueClick: () -> Unit, onLoginCheckPassed: () -> Unit
) {
    val viewModel: OnboardingViewModel = koinViewModel()
    val state by remember { viewModel.onboardingState }
    Crossfade(targetState = state, label = "") {
        when (it) {
            OnboardingState.Content -> Column(
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
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.size(OnboardingIconSize)
                    )
                    Spacer(modifier = Modifier.height(OnboardingIconSpacing))
                    Text(
                        text = stringResource(id = R.string.appName),
                        color = MaterialTheme.colorScheme.onBackground,
                        style = Title
                    )
                }
                Column(
                    modifier = Modifier.weight(OnboardingButtonWeight),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    TextButton(
                        text = stringResource(id = R.string.onboardingButton),
                        icon = painterResource(id = R.drawable.double_arrow_right),
                        onClick = onContinueClick
                    )
                }
            }

            OnboardingState.Success -> {
                LaunchedEffect(Unit) {
                    onLoginCheckPassed()
                }
            }

            OnboardingState.Initial -> Unit
        }
    }
}

