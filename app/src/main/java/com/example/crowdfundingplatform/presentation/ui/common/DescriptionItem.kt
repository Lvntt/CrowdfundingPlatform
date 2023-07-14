package com.example.crowdfundingplatform.presentation.ui.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.crowdfundingplatform.presentation.ui.theme.LabelRegularStyle
import com.example.crowdfundingplatform.presentation.ui.theme.PaddingSmall

@Composable
fun DescriptionItem(
    descriptionText: String,
    @DrawableRes descriptionIcon: Int
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(PaddingSmall),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = descriptionIcon),
            tint = MaterialTheme.colorScheme.onBackground,
            contentDescription = null
        )
        Text(
            text = descriptionText,
            style = LabelRegularStyle,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}