package com.example.crowdfundingplatform.presentation.ui.common

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
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import com.example.crowdfundingplatform.presentation.ui.theme.IconTextButtonContentPadding
import com.example.crowdfundingplatform.presentation.ui.theme.IconTextButtonCornerRadius
import com.example.crowdfundingplatform.presentation.ui.theme.IconTextButtonIconSize
import com.example.crowdfundingplatform.presentation.ui.theme.IconTextButtonStyle

@Composable
fun IconTextButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    text: String,
    icon: Painter,
    buttonColor: Color = MaterialTheme.colorScheme.primary,
    buttonContentColor: Color = MaterialTheme.colorScheme.onPrimary,
    buttonContentPadding: PaddingValues = PaddingValues(IconTextButtonContentPadding),
    buttonShape: Shape = RoundedCornerShape(IconTextButtonCornerRadius),
    buttonTextStyle: TextStyle = IconTextButtonStyle,
    buttonIconSize: Dp = IconTextButtonIconSize,
    iconContentDescription: String = ""
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        contentPadding = buttonContentPadding,
        shape = buttonShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor,
            contentColor = buttonContentColor
        )
    ) {
        Text(
            text = text,
            style = buttonTextStyle
        )
        Icon(
            painter = icon,
            contentDescription = iconContentDescription,
            modifier = Modifier.size(buttonIconSize)
        )
    }
}