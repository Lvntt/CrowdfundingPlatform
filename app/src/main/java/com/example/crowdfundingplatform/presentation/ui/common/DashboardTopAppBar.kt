package com.example.crowdfundingplatform.presentation.ui.common

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import com.example.crowdfundingplatform.R
import com.example.crowdfundingplatform.presentation.ui.theme.ElevationMedium
import com.example.crowdfundingplatform.presentation.ui.theme.PrimaryTextColor
import com.example.crowdfundingplatform.presentation.ui.theme.Subtitle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardTopAppBar(
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.dashboard),
                style = Subtitle,
                color = PrimaryTextColor
            )
        },
        modifier = modifier.shadow(ElevationMedium),
        scrollBehavior = scrollBehavior,
        colors = TopAppBarDefaults.largeTopAppBarColors(containerColor = MaterialTheme.colorScheme.background)
    )
}