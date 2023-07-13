package com.example.crowdfundingplatform.presentation.ui.screen.creation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.crowdfundingplatform.R
import com.example.crowdfundingplatform.presentation.ui.common.TextButton
import com.example.crowdfundingplatform.presentation.ui.theme.NextButtonHeight
import com.example.crowdfundingplatform.presentation.ui.theme.PaddingMedium
import com.example.crowdfundingplatform.presentation.ui.theme.RoundedCornerShapePercentMedium

@Composable
fun NextButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
    ) {
        TextButton(
            text = stringResource(id = R.string.next),
            onClick = onClick,
            buttonContentPadding = PaddingValues(start = PaddingMedium),
            buttonShape = RoundedCornerShape(RoundedCornerShapePercentMedium),
            modifier = Modifier.height(NextButtonHeight),
            icon = painterResource(id = R.drawable.arrow_right),
        )
    }
}