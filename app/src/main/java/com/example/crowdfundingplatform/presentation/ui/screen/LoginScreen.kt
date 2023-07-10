package com.example.crowdfundingplatform.presentation.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import com.example.crowdfundingplatform.R
import com.example.crowdfundingplatform.extension.noRippleClickable
import com.example.crowdfundingplatform.presentation.ui.common.CrowdfundingTopAppBar
import com.example.crowdfundingplatform.presentation.ui.common.LoginItem
import com.example.crowdfundingplatform.presentation.ui.common.TextButton
import com.example.crowdfundingplatform.presentation.ui.theme.LabelBoldStyle
import com.example.crowdfundingplatform.presentation.ui.theme.LabelLightStyle
import com.example.crowdfundingplatform.presentation.ui.theme.PaddingMedium
import com.example.crowdfundingplatform.presentation.ui.theme.PaddingSmall
import com.example.crowdfundingplatform.presentation.ui.theme.RegistrationFormVerticalPadding
import com.example.crowdfundingplatform.presentation.ui.theme.RoundedCornerShapePercentMedium
import com.example.crowdfundingplatform.presentation.ui.theme.Subtitle
import com.example.crowdfundingplatform.presentation.ui.theme.TextButtonMediumStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    onSignInClick: () -> Unit,
    onSignUpClick: () -> Unit,
    onNavigateUp: () -> Unit
) {
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
            modifier = Modifier.padding(vertical = RegistrationFormVerticalPadding)
        ) {
            LoginBody(
                onSignInClick = onSignInClick,
                onSignUpClick = onSignUpClick,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
private fun LoginBody(
    onSignInClick: () -> Unit,
    onSignUpClick: () -> Unit,
    modifier: Modifier = Modifier
) {
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
            onValueChange = {}
        )
        LoginItem(
            icon = ImageVector.vectorResource(id = R.drawable.lock),
            label = stringResource(id = R.string.password),
            onValueChange = {}
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
                    buttonShape = RoundedCornerShape(RoundedCornerShapePercentMedium),
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
                modifier = Modifier.noRippleClickable {
                    onSignUpClick()
                }
            )
        }
    }
}