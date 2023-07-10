package com.example.crowdfundingplatform.presentation.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import com.example.crowdfundingplatform.R
import com.example.crowdfundingplatform.presentation.ui.common.LoginItem
import com.example.crowdfundingplatform.presentation.ui.common.TextButton
import com.example.crowdfundingplatform.presentation.ui.theme.PrimaryTextColor
import com.example.crowdfundingplatform.presentation.ui.theme.LabelLightStyle
import com.example.crowdfundingplatform.presentation.ui.theme.PaddingMedium
import com.example.crowdfundingplatform.presentation.ui.theme.RegistrationFormVerticalPadding
import com.example.crowdfundingplatform.presentation.ui.theme.RoundedCornerShapePercentMedium
import com.example.crowdfundingplatform.presentation.ui.theme.TextButtonMediumStyle
import com.example.crowdfundingplatform.presentation.viewmodel.AuthViewModel

@Composable
fun RegistrationCredentialsScreen(
    authViewModel: AuthViewModel
) {
    Column(
        modifier = Modifier
            .padding(vertical = RegistrationFormVerticalPadding)
            .verticalScroll(
                rememberScrollState()
            )
    ) {
        RegistrationCredentialsBody(
            authViewModel = authViewModel
        )
    }
}

@Composable
private fun RegistrationCredentialsBody(
    authViewModel: AuthViewModel,
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
                buttonShape = RoundedCornerShape(RoundedCornerShapePercent),
                onClick = authViewModel::signUp
            )
        }
    }
}