package com.example.crowdfundingplatform.presentation.ui.screen

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import com.example.crowdfundingplatform.R
import com.example.crowdfundingplatform.presentation.ui.common.CrowdfundingTopAppBar
import com.example.crowdfundingplatform.presentation.ui.common.LoadingProgress
import com.example.crowdfundingplatform.presentation.ui.common.LoginItem
import com.example.crowdfundingplatform.presentation.ui.common.TextAlertDialog
import com.example.crowdfundingplatform.presentation.ui.common.TextButton
import com.example.crowdfundingplatform.presentation.ui.theme.DefaultTextColor
import com.example.crowdfundingplatform.presentation.ui.theme.LabelLightStyle
import com.example.crowdfundingplatform.presentation.ui.theme.PaddingMedium
import com.example.crowdfundingplatform.presentation.ui.theme.RegistrationFormVerticalPadding
import com.example.crowdfundingplatform.presentation.ui.theme.RoundedCornerShapePercent
import com.example.crowdfundingplatform.presentation.ui.theme.TextButtonMediumStyle
import com.example.crowdfundingplatform.presentation.uistate.AuthUiState
import com.example.crowdfundingplatform.presentation.viewmodel.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationCredentialsScreen(
    authViewModel: AuthViewModel,
    onSignUpClick: () -> Unit = authViewModel::signUp,
    onNavigateUp: () -> Unit,
    onSignUpSuccess: () -> Unit
) {
    val state by remember { authViewModel.authState }
    Crossfade(targetState = state) {
        when (it) {
            AuthUiState.Loading -> {
                LoadingProgress()
            }

            AuthUiState.Success -> {
                LaunchedEffect(Unit) {
                    onSignUpSuccess()
                }
            }

            else -> {
                if (state is AuthUiState.Error) {
                    TextAlertDialog(
                        title = stringResource(id = R.string.error),
                        text = stringResource(
                            id = (state as AuthUiState.Error).messageId
                        ),
                        dismissText = stringResource(id = R.string.ok),
                        onDismiss = authViewModel::resetErrorState
                    )
                }
                Scaffold(
                    topBar = {
                        CrowdfundingTopAppBar(
                            title = stringResource(id = R.string.registrationTitle),
                            canNavigateBack = true,
                            onNavigateUp = onNavigateUp
                        )
                    }
                ) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(vertical = RegistrationFormVerticalPadding)
                            .verticalScroll(
                                rememberScrollState()
                            )
                    ) {
                        RegistrationCredentialsBody(
                            authViewModel = authViewModel,
                            onSignUpClick = onSignUpClick,
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun RegistrationCredentialsBody(
    authViewModel: AuthViewModel,
    onSignUpClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val registrationState by remember { authViewModel.registrationContent }
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(PaddingMedium)
    ) {
        LoginItem(
            icon = ImageVector.vectorResource(id = R.drawable.email),
            label = stringResource(id = R.string.emailReq),
            onValueChange = authViewModel::setEmail,
            textFieldValue = registrationState.email
        )
        LoginItem(
            icon = ImageVector.vectorResource(id = R.drawable.lock),
            label = stringResource(id = R.string.passwordReq),
            onValueChange = authViewModel::setPassword,
            textFieldValue = registrationState.password
        )
        LoginItem(
            icon = ImageVector.vectorResource(id = R.drawable.lock),
            label = stringResource(id = R.string.confirmPasswordReq),
            onValueChange = authViewModel::setConfirmPassword,
            textFieldValue = registrationState.confirmPassword
        )
        Text(
            text = stringResource(id = R.string.requiredFields),
            color = DefaultTextColor,
            style = LabelLightStyle,
            modifier = Modifier.padding(start = PaddingMedium)
        )
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            TextButton(
                text = stringResource(id = R.string.signUp),
                buttonTextStyle = TextButtonMediumStyle,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(PaddingMedium),
                buttonShape = RoundedCornerShape(RoundedCornerShapePercent),
                onClick = onSignUpClick
            )
        }
    }
}