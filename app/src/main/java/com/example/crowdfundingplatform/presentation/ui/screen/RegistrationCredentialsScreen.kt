package com.example.crowdfundingplatform.presentation.ui.screen

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.crowdfundingplatform.R
import com.example.crowdfundingplatform.presentation.ui.common.CrowdfundingTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationCredentialsScreen() {
    Scaffold(
        topBar = {
            CrowdfundingTopAppBar(
                title = stringResource(id = R.string.registrationTitle),
                canNavigateBack = true
            )
        }
    ) { innerPadding ->

    }
}

@Composable
fun RegistrationCredentialsBody(
    modifier: Modifier = Modifier
) {
    
}