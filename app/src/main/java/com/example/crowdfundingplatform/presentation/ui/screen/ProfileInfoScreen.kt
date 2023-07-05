package com.example.crowdfundingplatform.presentation.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.example.crowdfundingplatform.R
import com.example.crowdfundingplatform.presentation.ui.theme.OnboardingIconSize
import com.example.crowdfundingplatform.presentation.ui.theme.PaddingMedium
import com.example.crowdfundingplatform.presentation.ui.theme.TextButtonLargeStyle
import com.example.crowdfundingplatform.presentation.ui.theme.TopAppBarStyle

@Composable
fun ProfileInfoScreen(
    name: String,
    email: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(PaddingMedium),
        verticalArrangement = Arrangement.spacedBy(PaddingMedium)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = Modifier.size(OnboardingIconSize),
                painter = painterResource(id = R.drawable.account_photo_placeholder),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onBackground
            )
        }
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = name,
            color = MaterialTheme.colorScheme.onBackground,
            style = TextButtonLargeStyle,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = email,
            color = MaterialTheme.colorScheme.onBackground,
            style = TopAppBarStyle,
            textAlign = TextAlign.Center
        )
    }
}