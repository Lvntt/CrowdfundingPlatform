package com.example.crowdfundingplatform.presentation.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.crowdfundingplatform.R
import com.example.crowdfundingplatform.common.Constants
import com.example.crowdfundingplatform.extension.noRippleClickable
import com.example.crowdfundingplatform.presentation.ui.theme.LabelBoldStyle
import com.example.crowdfundingplatform.presentation.ui.theme.LabelLightStyle
import com.example.crowdfundingplatform.presentation.ui.theme.PaddingExtraSmall
import com.example.crowdfundingplatform.presentation.ui.theme.PaddingSmall

@Composable
fun ProfileInfoItem(
    label: String,
    fieldValue: String?,
    onEditClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(PaddingSmall)
            .noRippleClickable {
                onEditClick()
            },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        Text(
            text = label,
            style = LabelBoldStyle,
            color = MaterialTheme.colorScheme.onBackground
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(PaddingExtraSmall),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = fieldValue ?: Constants.EMPTY_STRING,
                style = LabelLightStyle,
                color = MaterialTheme.colorScheme.onBackground
            )
            Icon(
                painter = painterResource(id = R.drawable.arrow_right),
                tint = MaterialTheme.colorScheme.onBackground,
                contentDescription = stringResource(id = R.string.editField)
            )
        }
    }
}