package com.example.crowdfundingplatform.presentation.ui.screen.profile.edit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.crowdfundingplatform.R
import com.example.crowdfundingplatform.presentation.ui.common.profile.EditFieldItem
import com.example.crowdfundingplatform.presentation.ui.common.button.TextButton
import com.example.crowdfundingplatform.presentation.ui.theme.PaddingMedium
import com.example.crowdfundingplatform.presentation.ui.theme.TextButtonSmallStyle

@Composable
fun EditEmailScreen(
    modifier: Modifier = Modifier
) {
    EditEmailContent(modifier = modifier)
}

@Composable
private fun EditEmailContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.verticalScroll(
            rememberScrollState()
        ),
        verticalArrangement = Arrangement.spacedBy(PaddingMedium)
    ) {
        Spacer(modifier = Modifier.height(PaddingMedium))
        EditFieldItem(
            label = stringResource(id = R.string.newEmail),
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
        Spacer(modifier = Modifier.height(PaddingMedium))
    }
}