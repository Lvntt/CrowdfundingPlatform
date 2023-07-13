package com.example.crowdfundingplatform.presentation.ui.screen.creation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.crowdfundingplatform.R
import com.example.crowdfundingplatform.presentation.ui.theme.DescriptionTextFieldHeight
import com.example.crowdfundingplatform.presentation.ui.theme.LabelRegularStyle
import com.example.crowdfundingplatform.presentation.ui.theme.PaddingLarge
import com.example.crowdfundingplatform.presentation.ui.theme.PaddingMedium
import com.example.crowdfundingplatform.presentation.ui.theme.PaddingSmall
import com.example.crowdfundingplatform.presentation.ui.theme.RoundedCornerShapePercentSmall
import com.example.crowdfundingplatform.presentation.ui.theme.Subtitle
import com.example.crowdfundingplatform.presentation.viewmodel.ProjectCreationViewModel

@Composable
fun DescriptionBody(
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
        val stepText = stringResource(id = R.string.step4of4)
        val desc = stringResource(id = R.string.description)

        Text(
            text = "$stepText $desc",
            style = Subtitle
        )

        Spacer(modifier = Modifier.height(PaddingLarge))

        Text(
            text = stringResource(id = R.string.descriptionHint),
            style = LabelRegularStyle
        )

        Spacer(modifier = Modifier.height(PaddingSmall))

        ProjectCreationItem(
            label = stringResource(id = R.string.description),
            onValueChange = projectCreationViewModel::setProjectDescription,
            textFieldValue = projectState.description,
            modifier = Modifier.height(DescriptionTextFieldHeight),
            singleLine = false,
            textFieldShape = RoundedCornerShape(RoundedCornerShapePercentSmall)
        )

        Spacer(modifier = Modifier.height(PaddingLarge))

        FinishButton(
            modifier = Modifier.align(Alignment.End),
            onClick = projectCreationViewModel::createProject
        )
    }
}