package com.example.crowdfundingplatform.presentation.ui.screen.creation.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.example.crowdfundingplatform.R
import com.example.crowdfundingplatform.presentation.ui.screen.creation.button.NextButton
import com.example.crowdfundingplatform.presentation.ui.screen.creation.textfield.ProjectCreationItem
import com.example.crowdfundingplatform.presentation.ui.theme.LabelRegularStyle
import com.example.crowdfundingplatform.presentation.ui.theme.PaddingLarge
import com.example.crowdfundingplatform.presentation.ui.theme.PaddingMedium
import com.example.crowdfundingplatform.presentation.ui.theme.PaddingSmall
import com.example.crowdfundingplatform.presentation.ui.theme.Subtitle
import com.example.crowdfundingplatform.presentation.ui.theme.SummaryTextFieldHeight
import com.example.crowdfundingplatform.presentation.viewmodel.ProjectCreationViewModel

@Composable
fun SummaryContent(
    projectCreationViewModel: ProjectCreationViewModel,
    modifier: Modifier = Modifier
) {
    val projectState by remember { projectCreationViewModel.project }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(PaddingMedium)
            .verticalScroll(rememberScrollState())
    ) {
        val stepText = stringResource(id = R.string.step1of4)
        val desc = stringResource(id = R.string.letsCreateProject)

        Text(
            text = "$stepText $desc",
            style = Subtitle
        )

        Spacer(modifier = Modifier.height(PaddingLarge))

        Text(
            text = stringResource(id = R.string.summaryHint),
            style = LabelRegularStyle
        )

        Spacer(modifier = Modifier.height(PaddingSmall))

        ProjectCreationItem(
            label = stringResource(id = R.string.titleLabel),
            onValueChange = projectCreationViewModel::setProjectTitle,
            textFieldValue = projectState.title
        )

        Spacer(modifier = Modifier.height(PaddingSmall))

        ProjectCreationItem(
            label = stringResource(id = R.string.amountLabel),
            onValueChange = projectCreationViewModel::setProjectTargetAmount,
            textFieldValue = projectState.targetAmount,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(PaddingSmall))

        ProjectCreationItem(
            label = stringResource(id = R.string.summaryLabel),
            onValueChange = projectCreationViewModel::setProjectSummary,
            textFieldValue = projectState.summary,
            modifier = Modifier.height(SummaryTextFieldHeight),
            singleLine = false
        )

        Spacer(modifier = Modifier.height(PaddingLarge))

        NextButton(
            modifier = Modifier.align(Alignment.End),
            onClick = projectCreationViewModel::openAvatar
        )
    }
}