package com.example.crowdfundingplatform.presentation.ui.screen.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.crowdfundingplatform.presentation.ui.extension.noRippleClickable
import com.example.crowdfundingplatform.presentation.ui.theme.LabelBoldStyle
import com.example.crowdfundingplatform.presentation.ui.theme.LabelLightStyle
import com.example.crowdfundingplatform.presentation.ui.theme.PaddingMedium
import com.example.crowdfundingplatform.presentation.ui.theme.PaddingSmall
import com.example.crowdfundingplatform.presentation.ui.theme.RegistrationFormVerticalPadding
import com.example.crowdfundingplatform.presentation.ui.theme.RoundedCornerShapePercentMedium
import com.example.crowdfundingplatform.presentation.ui.theme.Subtitle
import com.example.crowdfundingplatform.presentation.ui.theme.TextButtonMediumStyle
import com.example.crowdfundingplatform.presentation.viewmodel.AuthViewModel

@Composable
fun LoginContent(
    authViewModel: AuthViewModel,
    modifier: Modifier = Modifier
) {
    val loginState by remember { authViewModel.loginContent }
    Column(
        modifier = modifier, verticalArrangement = Arrangement.spacedBy(PaddingMedium)
    ) {
        Spacer(modifier = Modifier.height(RegistrationFormVerticalPadding))
        Text(
            text = stringResource(id = R.string.welcomeBack),
            style = Subtitle,
            modifier = Modifier.padding(horizontal = PaddingMedium)
        )
        LoginItem(
            icon = ImageVector.vectorResource(id = R.drawable.email_icon),
            label = stringResource(id = R.string.email),
            onValueChange = authViewModel::setLoginEmail,
            textFieldValue = loginState.email
        )
        LoginItem(
            icon = ImageVector.vectorResource(id = R.drawable.lock_icon),
            label = stringResource(id = R.string.password),
            onValueChange = authViewModel::setLoginPassword,
            textFieldValue = loginState.password
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
                    onClick = authViewModel::logIn
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.noAccount), style = LabelLightStyle
            )

            Spacer(modifier = Modifier.width(PaddingSmall))

            Text(text = stringResource(id = R.string.signUp),
                style = LabelBoldStyle,
                modifier = Modifier.noRippleClickable {
                    authViewModel.openRegistrationPersonalInfo()
                })
        }
        Spacer(modifier = Modifier.height(RegistrationFormVerticalPadding))
    }
}