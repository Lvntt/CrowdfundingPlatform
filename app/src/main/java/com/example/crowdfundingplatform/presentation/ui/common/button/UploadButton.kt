package com.example.crowdfundingplatform.presentation.ui.common.button

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.crowdfundingplatform.R
import com.example.crowdfundingplatform.presentation.ui.theme.LabelBoldStyleAlternative
import com.example.crowdfundingplatform.presentation.ui.theme.PaddingMedium
import com.example.crowdfundingplatform.presentation.ui.theme.PaddingSmall
import com.example.crowdfundingplatform.presentation.ui.theme.RoundedCornerShapePercentLarge
import com.example.crowdfundingplatform.presentation.ui.theme.TextButtonIconSizeMedium

@Composable
fun UploadButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    @DrawableRes iconId: Int = R.drawable.file_upload_icon,
    text: String = stringResource(id = R.string.upload),
    containerColor: Color = MaterialTheme.colorScheme.tertiaryContainer,
    contentColor: Color = MaterialTheme.colorScheme.onTertiaryContainer
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        contentPadding = PaddingValues(start = PaddingSmall, end = PaddingMedium),
        shape = RoundedCornerShape(RoundedCornerShapePercentLarge),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        )
    ) {
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = null,
            modifier = Modifier.size(TextButtonIconSizeMedium)
        )
        Text(
            text = text,
            style = LabelBoldStyleAlternative
        )
    }
}