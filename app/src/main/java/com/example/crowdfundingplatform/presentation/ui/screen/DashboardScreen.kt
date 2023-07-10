package com.example.crowdfundingplatform.presentation.ui.screen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.crowdfundingplatform.R
import com.example.crowdfundingplatform.data.mock.MockProjectSource
import com.example.crowdfundingplatform.domain.entity.ProjectSummary
import com.example.crowdfundingplatform.presentation.ui.common.TextButton
import com.example.crowdfundingplatform.presentation.ui.theme.AdditionalTextColor
import com.example.crowdfundingplatform.presentation.ui.theme.CrowdfundingPlatformTheme
import com.example.crowdfundingplatform.presentation.ui.theme.DividerColor
import com.example.crowdfundingplatform.presentation.ui.theme.ElevationMedium
import com.example.crowdfundingplatform.presentation.ui.theme.ExtraSmallLabelRegularStyleAlternative
import com.example.crowdfundingplatform.presentation.ui.theme.Headline
import com.example.crowdfundingplatform.presentation.ui.theme.LabelBoldStyleAlternative
import com.example.crowdfundingplatform.presentation.ui.theme.LabelRegularStyleAlternative
import com.example.crowdfundingplatform.presentation.ui.theme.OnSecondaryElementColorLight
import com.example.crowdfundingplatform.presentation.ui.theme.OnTertiaryContainerLight
import com.example.crowdfundingplatform.presentation.ui.theme.PaddingExtraSmall
import com.example.crowdfundingplatform.presentation.ui.theme.PaddingMedium
import com.example.crowdfundingplatform.presentation.ui.theme.PaddingSmall
import com.example.crowdfundingplatform.presentation.ui.theme.PaddingTiny
import com.example.crowdfundingplatform.presentation.ui.theme.ProjectStatsButtonHeight
import com.example.crowdfundingplatform.presentation.ui.theme.ProjectSummaryImageHeight
import com.example.crowdfundingplatform.presentation.ui.theme.RoundedCornerShapePercentLarge
import com.example.crowdfundingplatform.presentation.ui.theme.SecondaryElementColorLight
import com.example.crowdfundingplatform.presentation.ui.theme.SecondaryTextColor
import com.example.crowdfundingplatform.presentation.ui.theme.SmallLabelRegularStyleAlternative
import com.example.crowdfundingplatform.presentation.ui.theme.TertiaryContainerLight
import com.example.crowdfundingplatform.presentation.ui.theme.TextButtonIconSizeMedium

@Composable
fun DashboardScreen(
    projects: List<ProjectSummary>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        items(projects) { project ->
            ProjectCard(
                project = project,
                modifier = modifier
            )
        }
    }
}

@Composable
private fun ProjectCard(
    project: ProjectSummary,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(PaddingMedium),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = ElevationMedium)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            ProjectImage(
                imageDrawableId = project.imageDrawableId
            )
            ProjectBody(
                title = project.title,
                summary = project.summary
            )
            Divider(color = DividerColor)
            ProjectStats(
                collectedAmount = project.collectedAmount,
                targetAmount = project.targetAmount
            )
        }
    }
}

@Composable
private fun ProjectImage(
    @DrawableRes
    imageDrawableId: Int
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(ProjectSummaryImageHeight)
            .background(Color.Cyan)
    ) {
        Image(
            painter = painterResource(id = imageDrawableId),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun ProjectBody(
    title: String,
    summary: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(PaddingMedium)
    ) {
        Text(
            text = title,
            style = Headline,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = summary,
            style = LabelRegularStyleAlternative,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Composable
private fun ProjectStats(
    collectedAmount: Int,
    targetAmount: Int
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
                    text = "${collectedAmount * 100 / targetAmount}%",
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
            text = stringResource(id = R.string.viewDetails),
            icon = painterResource(id = R.drawable.arrow_right),
            buttonTextStyle = LabelBoldStyleAlternative,
            buttonColor = TertiaryContainerLight,
            buttonContentColor = OnTertiaryContainerLight,
            buttonContentPadding = PaddingValues(horizontal = PaddingMedium),
            buttonShape = RoundedCornerShape(RoundedCornerShapePercentLarge),
            buttonIconSize = TextButtonIconSizeMedium,
            modifier = Modifier
                .height(ProjectStatsButtonHeight)
                .align(Alignment.CenterVertically)
        )
    }
}

@Preview
@Composable
private fun DashboardPreview() {
    CrowdfundingPlatformTheme {
        DashboardScreen(projects = MockProjectSource.projects)
    }
}