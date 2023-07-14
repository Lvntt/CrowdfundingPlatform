package com.example.crowdfundingplatform.presentation.ui.common

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.crowdfundingplatform.R
import com.example.crowdfundingplatform.presentation.ui.theme.AdditionalTextColor
import com.example.crowdfundingplatform.presentation.ui.theme.ExtraSmallLabelRegularStyleAlternative
import com.example.crowdfundingplatform.presentation.ui.theme.LabelBoldStyleAlternative
import com.example.crowdfundingplatform.presentation.ui.theme.OnSecondaryElementColorLight
import com.example.crowdfundingplatform.presentation.ui.theme.OnTertiaryContainerLight
import com.example.crowdfundingplatform.presentation.ui.theme.PaddingExtraSmall
import com.example.crowdfundingplatform.presentation.ui.theme.PaddingMedium
import com.example.crowdfundingplatform.presentation.ui.theme.PaddingSmall
import com.example.crowdfundingplatform.presentation.ui.theme.PaddingTiny
import com.example.crowdfundingplatform.presentation.ui.theme.ProjectStatsButtonHeight
import com.example.crowdfundingplatform.presentation.ui.theme.RoundedCornerShapePercentLarge
import com.example.crowdfundingplatform.presentation.ui.theme.SecondaryElementColorLight
import com.example.crowdfundingplatform.presentation.ui.theme.SecondaryTextColor
import com.example.crowdfundingplatform.presentation.ui.theme.SmallLabelRegularStyleAlternative
import com.example.crowdfundingplatform.presentation.ui.theme.TertiaryContainerLight
import com.example.crowdfundingplatform.presentation.ui.theme.TextButtonIconSizeMedium
import java.math.BigDecimal

@Composable
fun ProjectStats(
    collectedAmount: BigDecimal,
    targetAmount: BigDecimal,
    @StringRes buttonText: Int,
    onButtonClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .padding(horizontal = PaddingMedium),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(PaddingMedium)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(vertical = PaddingMedium),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(PaddingSmall)
            ) {
                Text(
                    text = stringResource(id = R.string.raisedOf),
                    style = LabelBoldStyleAlternative,
                    color = SecondaryTextColor
                )
                Box(
                    modifier = Modifier.background(
                        color = SecondaryElementColorLight,
                        shape = RoundedCornerShape(RoundedCornerShapePercentLarge)
                    )
                ) {
                    Text(
                        text = stringResource(id = R.string.raiseGoal),
                        style = ExtraSmallLabelRegularStyleAlternative,
                        color = OnSecondaryElementColorLight,
                        modifier = Modifier.padding(PaddingExtraSmall)
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(vertical = PaddingMedium),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(PaddingSmall)
            ) {
                Text(
                    text = "${collectedAmount.multiply(BigDecimal.valueOf(100)).divideToIntegralValue(targetAmount)}%",
                    style = LabelBoldStyleAlternative,
                    color = Color.Black,
                )
                Text(
                    text = "$targetAmount DENEG",
                    style = SmallLabelRegularStyleAlternative,
                    color = AdditionalTextColor,
                    modifier = Modifier.padding(top = PaddingTiny)
                )
            }
        }

        TextButton(
            text = stringResource(id = buttonText),
            icon = painterResource(id = R.drawable.arrow_right),
            buttonTextStyle = LabelBoldStyleAlternative,
            buttonColor = TertiaryContainerLight,
            buttonContentColor = OnTertiaryContainerLight,
            buttonContentPadding = PaddingValues(horizontal = PaddingMedium),
            buttonShape = RoundedCornerShape(RoundedCornerShapePercentLarge),
            buttonIconSize = TextButtonIconSizeMedium,
            onClick = onButtonClick,
            modifier = Modifier
                .height(ProjectStatsButtonHeight)
                .align(Alignment.CenterVertically)
        )
    }
}