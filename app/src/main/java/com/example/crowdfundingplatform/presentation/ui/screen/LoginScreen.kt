package com.example.crowdfundingplatform.presentation.ui.screen

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
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
import com.example.crowdfundingplatform.presentation.ui.theme.LabelBoldStyle
import com.example.crowdfundingplatform.presentation.ui.theme.LabelLightStyle
import com.example.crowdfundingplatform.presentation.ui.theme.PaddingMedium
import com.example.crowdfundingplatform.presentation.ui.theme.PaddingSmall
import com.example.crowdfundingplatform.presentation.ui.theme.RegistrationFormVerticalPadding
import com.example.crowdfundingplatform.presentation.ui.theme.RoundedCornerShapePercent
import com.example.crowdfundingplatform.presentation.ui.theme.Subtitle
import com.example.crowdfundingplatform.presentation.ui.theme.TextButtonMediumStyle
import com.example.crowdfundingplatform.presentation.uistate.AuthUiState
import com.example.crowdfundingplatform.presentation.viewmodel.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    authViewModel: AuthViewModel,
    onSignInClick: () -> Unit = authViewModel::logIn,
    onSignUpClick: () -> Unit,
    onNavigateUp: () -> Unit,
    onSignInSuccess: () -> Unit
) {
    val state by remember { authViewModel.authState }
    Crossfade(targetState = state) {
        when (it) {
            AuthUiState.Loading -> {
                LoadingProgress()
            }

            AuthUiState.Success -> {
                LaunchedEffect(Unit) {
                    onSignInSuccess()
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
                            title = stringResource(id = R.string.signIn),
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
                        LoginBody(
                            authViewModel = authViewModel,
                            onSignInClick = onSignInClick,
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
private fun LoginBody(
    authViewModel: AuthViewModel,
    onSignInClick: () -> Unit,
    onSignUpClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val email by remember { authViewModel.loginEmail }
    val password by remember { authViewModel.loginPassword }
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(PaddingMedium)
    ) {
        Text(
            text = stringResource(id = R.string.welcomeBack),
            style = Subtitle,
            modifier = Modifier.padding(horizontal = PaddingMedium)
        )
        LoginItem(
            icon = ImageVector.vectorResource(id = R.drawable.email),
            label = stringResource(id = R.string.email),
            onValueChange = authViewModel::setLoginEmail,
            textFieldValue = email
        )
        LoginItem(
            icon = ImageVector.vectorResource(id = R.drawable.lock),
            label = stringResource(id = R.string.password),
            onValueChange = authViewModel::setLoginPassword,
            textFieldValue = password
        )
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                TextButton(
                    text = stringResource(id = R.string.signIn),
                    buttonTextStyle = TextButtonMediumStyle,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(PaddingMedium),
                    buttonShape = RoundedCornerShape(RoundedCornerShapePercent),
                    onClick = onSignInClick
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.noAccount),
                style = LabelLightStyle
            )

            Spacer(modifier = Modifier.width(PaddingSmall))

            Text(
                text = stringResource(id = R.string.signUp),
                style = LabelBoldStyle,
                modifier = Modifier.clickable {
                    onSignUpClick()
                }
            )
        }
    }
}