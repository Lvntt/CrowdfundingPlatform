package com.example.crowdfundingplatform.presentation.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.example.crowdfundingplatform.presentation.ui.common.CrowdfundingTopAppBar
import com.example.crowdfundingplatform.presentation.ui.common.LoginItem
import com.example.crowdfundingplatform.presentation.ui.common.TextButton
import com.example.crowdfundingplatform.presentation.ui.theme.PrimaryTextColor
import com.example.crowdfundingplatform.presentation.ui.theme.LabelLightStyle
import com.example.crowdfundingplatform.presentation.ui.theme.PaddingMedium
import com.example.crowdfundingplatform.presentation.ui.theme.RegistrationFormVerticalPadding
import com.example.crowdfundingplatform.presentation.ui.theme.RoundedCornerShapePercentMedium
import com.example.crowdfundingplatform.presentation.ui.theme.TextButtonMediumStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationCredentialsScreen(
    onSignUpClick: () -> Unit,
    onNavigateUp: () -> Unit
) {
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
            modifier = Modifier.padding(vertical = RegistrationFormVerticalPadding)
        ) {
            RegistrationCredentialsBody(
                onSignUpClick = onSignUpClick,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
private fun RegistrationCredentialsBody(
    onSignUpClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(PaddingMedium)
    ) {
        LoginItem(
            icon = ImageVector.vectorResource(id = R.drawable.email),
            label = stringResource(id = R.string.emailReq),
            onValueChange = {}
        )
        LoginItem(
            icon = ImageVector.vectorResource(id = R.drawable.lock),
            label = stringResource(id = R.string.passwordReq),
            onValueChange = {}
        )
        LoginItem(
            icon = ImageVector.vectorResource(id = R.drawable.lock),
            label = stringResource(id = R.string.confirmPasswordReq),
            onValueChange = {}
        )
        Text(
            text = stringResource(id = R.string.requiredFields),
            color = PrimaryTextColor,
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
                buttonShape = RoundedCornerShape(RoundedCornerShapePercentMedium),
                onClick = onSignUpClick
            )
        }
    }
}