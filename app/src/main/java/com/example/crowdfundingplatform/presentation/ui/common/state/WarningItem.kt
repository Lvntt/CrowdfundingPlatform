package com.example.crowdfundingplatform.presentation.ui.common.state

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.crowdfundingplatform.R
import com.example.crowdfundingplatform.presentation.ui.theme.LabelBoldStyle
import com.example.crowdfundingplatform.presentation.ui.theme.PaddingMedium
import com.example.crowdfundingplatform.presentation.ui.theme.PaddingSmall
import com.example.crowdfundingplatform.presentation.ui.theme.TextButtonLargeCornerRadius

@Composable
fun WarningItem(
    warningText: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(
                TextButtonLargeCornerRadius))
            .background(MaterialTheme.colorScheme.primary)
            .padding(PaddingSmall),
        horizontalArrangement = Arrangement.spacedBy(PaddingMedium),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.warning_icon),
            tint = MaterialTheme.colorScheme.onBackground,
            contentDescription = stringResource(id = R.string.editField)
        )
        Text(
            text = warningText,
            style = LabelBoldStyle,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}