package com.example.crowdfundingplatform.presentation.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.crowdfundingplatform.R
import com.example.crowdfundingplatform.presentation.ui.common.CrowdfundingTopAppBar
import com.example.crowdfundingplatform.presentation.ui.common.EditFieldItem
import com.example.crowdfundingplatform.presentation.ui.common.TextButton
import com.example.crowdfundingplatform.presentation.ui.theme.CrowdfundingPlatformTheme
import com.example.crowdfundingplatform.presentation.ui.theme.PaddingLarge
import com.example.crowdfundingplatform.presentation.ui.theme.PaddingMedium
import com.example.crowdfundingplatform.presentation.ui.theme.TextButtonSmallStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditPasswordScreen(
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            CrowdfundingTopAppBar(
                title = stringResource(id = R.string.editPassword),
                canNavigateBack = true,
                onNavigateUp = onNavigateUp
            )
        }
    ) { innerPadding ->
        EditPasswordBody(modifier = modifier.padding(innerPadding))
    }
}

@Composable
fun EditPasswordBody(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(vertical = PaddingLarge),
        verticalArrangement = Arrangement.spacedBy(PaddingMedium)
    ) {
        EditFieldItem(
            label = stringResource(id = R.string.newPassword),
            onValueChange = {}
        )
        EditFieldItem(
            label = stringResource(id = R.string.confirmNewPassword),
            onValueChange = {}
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = PaddingMedium),
            contentAlignment = Alignment.Center
        ) {
            TextButton(
                text = stringResource(id = R.string.confirm),
                buttonTextStyle = TextButtonSmallStyle
            )
        }
    }
}

@Preview
@Composable
private fun EditPasswordPreview() {
    CrowdfundingPlatformTheme {
        EditPasswordScreen({})
    }
}