package com.example.crowdfundingplatform.presentation.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
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
import com.example.crowdfundingplatform.presentation.ui.theme.PaddingMedium
import com.example.crowdfundingplatform.presentation.ui.theme.RegistrationFormVerticalPadding

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationPersonalInfoScreen() {
    Scaffold(
        topBar = {
            CrowdfundingTopAppBar(
                title = stringResource(id = R.string.registrationTitle),
                canNavigateBack = true
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(vertical = RegistrationFormVerticalPadding)
        ) {
            RegistrationPersonalInfoBody(modifier = Modifier.padding(innerPadding))
        }
    }
}

@Composable
fun RegistrationPersonalInfoBody(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(PaddingMedium)
    ) {
        LoginItem(
            icon = ImageVector.vectorResource(id = R.drawable.person_outline),
            label = stringResource(id = R.string.nameReq),
            onValueChange = {}
        )
        LoginItem(
            icon = ImageVector.vectorResource(id = R.drawable.person_outline),
            label = stringResource(id = R.string.surnameReq),
            onValueChange = {}
        )
        LoginItem(
            icon = ImageVector.vectorResource(id = R.drawable.person_outline),
            label = stringResource(id = R.string.patronymicReq),
            onValueChange = {}
        )
        Text(
            text = stringResource(id = R.string.requiredFields),
            modifier = Modifier.padding(start = PaddingMedium)
        )
    }
}