package com.example.crowdfundingplatform.presentation.ui.screen.project

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.crowdfundingplatform.R
import com.example.crowdfundingplatform.common.Constants
import com.example.crowdfundingplatform.domain.entity.project.ProjectInfo
import com.example.crowdfundingplatform.presentation.mapper.ProjectCategoryToDescriptionRes
import com.example.crowdfundingplatform.presentation.ui.common.DescriptionItem
import com.example.crowdfundingplatform.presentation.ui.common.dialog.InputTextDialog
import com.example.crowdfundingplatform.presentation.ui.common.dialog.LoadingDialog
import com.example.crowdfundingplatform.presentation.ui.common.ProjectStats
import com.example.crowdfundingplatform.presentation.ui.common.dialog.TextAlertDialog
import com.example.crowdfundingplatform.presentation.ui.common.state.ErrorScreen
import com.example.crowdfundingplatform.presentation.ui.common.state.LoadingProgress
import com.example.crowdfundingplatform.presentation.ui.theme.ElevationMedium
import com.example.crowdfundingplatform.presentation.ui.theme.Headline
import com.example.crowdfundingplatform.presentation.ui.theme.LabelRegularStyleAlternative
import com.example.crowdfundingplatform.presentation.ui.theme.PaddingMedium
import com.example.crowdfundingplatform.presentation.ui.theme.PaddingSmall
import com.example.crowdfundingplatform.presentation.ui.theme.ProgressDialogSize
import com.example.crowdfundingplatform.presentation.ui.theme.Subtitle
import com.example.crowdfundingplatform.presentation.ui.theme.TextButtonLargeCornerRadius
import com.example.crowdfundingplatform.presentation.uistate.project.FundProjectState
import com.example.crowdfundingplatform.presentation.uistate.project.ProjectInfoState
import com.example.crowdfundingplatform.presentation.viewmodel.ProjectInfoViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun ProjectInfoScreen(
    projectId: String,
    viewModel: ProjectInfoViewModel = koinViewModel { parametersOf(projectId) },
    onSignedOut: () -> Unit
) {
    val projectState by remember { viewModel.projectInfoState }
    Crossfade(targetState = projectState, label = "") {
        when (it) {
            is ProjectInfoState.Content -> ProjectInfoContent(
                viewModel = viewModel, projectInfo = it.projectInfo, onSignedOut = onSignedOut
            )

            is ProjectInfoState.Error -> ErrorScreen(
                messageId = it.messageId, onRetryClick = viewModel::getProject
            )

            ProjectInfoState.Loading -> LoadingProgress()
        }
    }
}

@Composable
fun ProjectInfoContent(
    viewModel: ProjectInfoViewModel, projectInfo: ProjectInfo, onSignedOut: () -> Unit
) {
    var shouldShowFundingDialog by remember { mutableStateOf(false) }
    val fundingState by remember { viewModel.projectFundState }

    if (fundingState is FundProjectState.Input && shouldShowFundingDialog) {
        InputTextDialog(
            title = stringResource(id = R.string.sponsorProject),
            editFieldLabel = stringResource(id = R.string.moneyAmount),
            text = (fundingState as FundProjectState.Input).moneyAmount,
            onTextValueChange = viewModel::setFundMoneyAmount,
            onConfirm = {
                shouldShowFundingDialog = false
                viewModel.fundProject()
            },
            confirmText = stringResource(id = R.string.fund),
            onDismiss = {
                shouldShowFundingDialog = false
            },
            dismissText = stringResource(id = R.string.cancel)
        )
    }
    if (fundingState == FundProjectState.Loading) {
        LoadingDialog()
    }
    if (fundingState is FundProjectState.Error) {
        TextAlertDialog(title = stringResource(id = R.string.error),
            text = stringResource(id = (fundingState as FundProjectState.Error).messageId),
            confirmText = stringResource(id = R.string.ok),
            onConfirm = {
                viewModel.resetFundingState()
            },
            onDismiss = {
                viewModel.resetFundingState()
            })
    }
    if (fundingState == FundProjectState.Success) {
        FundingSuccessDialog {
            viewModel.resetFundingState()
        }
    }
    if (fundingState == FundProjectState.SignedOut) {
        LaunchedEffect(Unit) {
            onSignedOut()
        }
    }
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(PaddingSmall),
        contentPadding = PaddingValues(PaddingMedium)
    ) {
        item {
            ProjectInfoTopPart(projectInfo) { shouldShowFundingDialog = true }
        }
        items(count = projectInfo.attachmentIds.size) { index ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = ElevationMedium)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data("${Constants.BASE_URL}${Constants.FILE_URL}${projectInfo.attachmentIds[index]}")
                        .crossfade(true).build(),
                    error = painterResource(id = R.drawable.ic_broken_image),
                    placeholder = painterResource(R.drawable.loading_img),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }

}

@Composable
private fun ProjectInfoTopPart(projectInfo: ProjectInfo, onFundClick: () -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(PaddingSmall)) {
        Text(
            text = projectInfo.title,
            style = Subtitle,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = projectInfo.summary,
            style = LabelRegularStyleAlternative,
            color = MaterialTheme.colorScheme.onBackground
        )
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = ElevationMedium)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data("${Constants.BASE_URL}${Constants.FILE_URL}${projectInfo.avatarId}")
                    .crossfade(true).build(),
                error = painterResource(id = R.drawable.ic_broken_image),
                placeholder = painterResource(R.drawable.loading_img),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()
            )
            ProjectStats(
                projectInfo.collectedAmount, projectInfo.targetAmount, R.string.fund, onFundClick
            )
        }
        Text(
            text = stringResource(id = R.string.aboutProject),
            style = Headline,
            color = MaterialTheme.colorScheme.onBackground
        )
        val categoryId = ProjectCategoryToDescriptionRes.descriptions[projectInfo.category]
        if (categoryId != null) {
            DescriptionItem(
                descriptionText = stringResource(
                    id = R.string.categoryTemplate, stringResource(
                        id = categoryId
                    )
                ), descriptionIcon = R.drawable.category_icon
            )
        }
        DescriptionItem(
            descriptionText = stringResource(id = R.string.descriptionWithSemicolon),
            descriptionIcon = R.drawable.info_icon
        )
        Text(
            text = projectInfo.description,
            style = LabelRegularStyleAlternative,
            color = MaterialTheme.colorScheme.onBackground
        )
        DescriptionItem(
            descriptionText = stringResource(
                id = R.string.finishDate, projectInfo.finishDate
            ), descriptionIcon = R.drawable.time_icon
        )
        if (projectInfo.attachmentIds.isNotEmpty()) {
            DescriptionItem(
                descriptionText = stringResource(id = R.string.attachments),
                descriptionIcon = R.drawable.image_icon
            )
        }
    }
}

@Composable
private fun FundingSuccessDialog(
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss,
        DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(ProgressDialogSize)
                .background(
                    MaterialTheme.colorScheme.background, shape = RoundedCornerShape(
                        TextButtonLargeCornerRadius
                    )
                )
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(PaddingMedium)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.funding_success_icon),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = stringResource(id = R.string.fundingSuccessful),
                    style = Subtitle,
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}