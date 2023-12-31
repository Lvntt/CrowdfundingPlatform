package com.example.crowdfundingplatform.presentation.ui.screen.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.crowdfundingplatform.R
import com.example.crowdfundingplatform.common.Constants
import com.example.crowdfundingplatform.domain.entity.project.ProjectSummary
import com.example.crowdfundingplatform.presentation.ui.common.ProjectStats
import com.example.crowdfundingplatform.presentation.ui.theme.DividerColor
import com.example.crowdfundingplatform.presentation.ui.theme.ElevationMedium
import com.example.crowdfundingplatform.presentation.ui.theme.Headline
import com.example.crowdfundingplatform.presentation.ui.theme.LabelBoldStyle
import com.example.crowdfundingplatform.presentation.ui.theme.LabelRegularStyle
import com.example.crowdfundingplatform.presentation.ui.theme.LabelRegularStyleAlternative
import com.example.crowdfundingplatform.presentation.ui.theme.PaddingMedium
import com.example.crowdfundingplatform.presentation.ui.theme.ProjectSummaryImageHeight
import com.example.crowdfundingplatform.presentation.viewmodel.DashboardViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun DashboardScreen(
    modifier: Modifier = Modifier,
    onNavigateToProject: (String) -> Unit,
    dashboardViewModel: DashboardViewModel = koinViewModel()
) {
    val projects = dashboardViewModel.projects.collectAsLazyPagingItems()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        items(
            count = projects.itemCount,
            key = projects.itemKey { it.id },
            contentType = projects.itemContentType { "Projects" }
        ) { index ->
            val project = projects[index]
            project?.let {
                ProjectCard(
                    project = project,
                    onNavigateToProject = { onNavigateToProject(project.id) })
            }
        }

        when (projects.loadState.append) {
            is LoadState.Error -> item { ErrorItem() }
            is LoadState.Loading -> item { LoadingItem() }
            is LoadState.NotLoading -> Unit
        }
    }

    when (projects.loadState.refresh) {
        is LoadState.Error -> ErrorScreen(
            onRetry = {
                projects.refresh()
            }
        )

        is LoadState.Loading -> LoadingScreen()
        is LoadState.NotLoading -> Unit
    }
}

@Composable
private fun ProjectCard(
    project: ProjectSummary,
    onNavigateToProject: () -> Unit,
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
            ProjectImage(imageId = project.avatarId)
            ProjectContent(
                title = project.title,
                summary = project.summary
            )
            Divider(color = DividerColor)
            ProjectStats(
                collectedAmount = project.collectedAmount,
                targetAmount = project.targetAmount,
                onButtonClick = onNavigateToProject,
                buttonText = R.string.viewDetails
            )
        }
    }
}

@Composable
private fun ProjectImage(
    imageId: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(ProjectSummaryImageHeight)
    ) {
        val imageLink = "${Constants.BASE_URL}${Constants.FILE_URL}$imageId"

        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(imageLink)
                .crossfade(true)
                .build(),
            error = painterResource(id = R.drawable.ic_broken_image),
            placeholder = painterResource(R.drawable.loading_img),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun ProjectContent(
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
private fun LoadingItem() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(modifier = Modifier.padding(PaddingMedium))
    }
}

@Composable
private fun ErrorItem() {
    Text(
        text = stringResource(id = R.string.anErrorOccurred),
        style = LabelBoldStyle
    )
}

@Composable
private fun ErrorScreen(
    onRetry: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.errorWithDesc),
            style = LabelBoldStyle
        )
        Button(
            onClick = onRetry
        ) {
            Text(
                text = stringResource(id = R.string.retry),
                style = LabelRegularStyle
            )
        }
    }
}

@Composable
private fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(modifier = Modifier.padding(PaddingMedium))
    }
}