package com.example.crowdfundingplatform.presentation.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.example.crowdfundingplatform.presentation.ui.theme.DefaultTextColor
import com.example.crowdfundingplatform.presentation.ui.theme.LabelLightStyle
import com.example.crowdfundingplatform.presentation.ui.theme.PaddingMedium
import com.example.crowdfundingplatform.presentation.ui.theme.RegistrationFormVerticalPadding
import com.example.crowdfundingplatform.presentation.ui.theme.RoundedCornerShapePercent
import com.example.crowdfundingplatform.presentation.ui.theme.TextButtonMediumStyle
import com.example.crowdfundingplatform.presentation.viewmodel.AuthViewModel

@Composable
fun RegistrationPersonalInfoScreen(
    authViewModel: AuthViewModel,
    onContinueClick: () -> Unit
) {
    Column(
        modifier = Modifier.padding(vertical = RegistrationFormVerticalPadding)
    ) {
        RegistrationPersonalInfoBody(
            authViewModel = authViewModel,
            onContinueClick = onContinueClick,
        )
    }
}

@Composable
private fun RegistrationPersonalInfoBody(
    authViewModel: AuthViewModel,
    onContinueClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val registrationState by remember { authViewModel.registrationContent }
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(PaddingMedium)
    ) {
        LoginItem(
            icon = ImageVector.vectorResource(id = R.drawable.person_outline),
            label = stringResource(id = R.string.nameReq),
            onValueChange = authViewModel::setName,
            textFieldValue = registrationState.name
        )
        LoginItem(
            icon = ImageVector.vectorResource(id = R.drawable.person_outline),
            label = stringResource(id = R.string.surnameReq),
            onValueChange = authViewModel::setSurname,
            textFieldValue = registrationState.surname
        )
        LoginItem(
            icon = ImageVector.vectorResource(id = R.drawable.person_outline),
            label = stringResource(id = R.string.patronymicReq),
            onValueChange = authViewModel::setPatronymic,
            textFieldValue = registrationState.patronymic
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
                text = stringResource(id = R.string.continueRegistration),
                buttonTextStyle = TextButtonMediumStyle,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(PaddingMedium),
                buttonShape = RoundedCornerShape(RoundedCornerShapePercent),
                onClick = onContinueClick
            )
        }
    }
}