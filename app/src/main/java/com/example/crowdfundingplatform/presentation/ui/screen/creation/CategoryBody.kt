package com.example.crowdfundingplatform.presentation.ui.screen.creation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.crowdfundingplatform.R
import com.example.crowdfundingplatform.data.datasource.AvailableCategories
import com.example.crowdfundingplatform.presentation.ui.theme.LabelRegularStyle
import com.example.crowdfundingplatform.presentation.ui.theme.PaddingLarge
import com.example.crowdfundingplatform.presentation.ui.theme.PaddingMedium
import com.example.crowdfundingplatform.presentation.ui.theme.Subtitle
import com.example.crowdfundingplatform.presentation.viewmodel.ProjectCreationViewModel

@Composable
fun CategoryBody(
    projectCreationViewModel: ProjectCreationViewModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(PaddingMedium)
            .verticalScroll(rememberScrollState())
    ) {
        val stepText = stringResource(id = R.string.step3of4)
        val desc = stringResource(id = R.string.category)

        Text(
            text = "$stepText $desc",
            style = Subtitle
        )

        Spacer(modifier = Modifier.height(PaddingLarge))

        Text(
            text = stringResource(id = R.string.categoryHint),
            style = LabelRegularStyle
        )

        Spacer(modifier = Modifier.height(PaddingLarge))

        CategoryDropdownMenu(
            projectCreationViewModel = projectCreationViewModel,
            availableCategories = AvailableCategories.categories,
            onDropdownMenuItemClick = projectCreationViewModel::setProjectCategory
        )

        Spacer(modifier = Modifier.height(PaddingLarge))

        NextButton(
            modifier = Modifier.align(Alignment.End),
            onClick = projectCreationViewModel::openDescription
        )
    }
}