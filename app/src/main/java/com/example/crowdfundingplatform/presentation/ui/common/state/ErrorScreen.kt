package com.example.crowdfundingplatform.presentation.ui.common.state

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.crowdfundingplatform.R
import com.example.crowdfundingplatform.common.Constants
import com.example.crowdfundingplatform.presentation.ui.extension.noRippleClickable
import com.example.crowdfundingplatform.presentation.ui.theme.PaddingLarge
import com.example.crowdfundingplatform.presentation.ui.theme.ProgressIndicatorSize
import com.example.crowdfundingplatform.presentation.ui.theme.Subtitle

@Composable
fun ErrorScreen(@StringRes messageId: Int, onRetryClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Icon(
            painter = painterResource(id = R.drawable.refresh_icon),
            contentDescription = Constants.EMPTY_STRING,
            modifier = Modifier
                .size(ProgressIndicatorSize)
                .noRippleClickable(onRetryClick)
        )
        Spacer(modifier = Modifier.height(PaddingLarge))
        Text(
            text = stringResource(id = messageId),
            style = Subtitle,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center
        )
    }
}