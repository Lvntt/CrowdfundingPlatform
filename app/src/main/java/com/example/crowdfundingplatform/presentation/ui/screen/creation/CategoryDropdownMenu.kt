package com.example.crowdfundingplatform.presentation.ui.screen.creation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.crowdfundingplatform.R
import com.example.crowdfundingplatform.domain.entity.ProjectCategory
import com.example.crowdfundingplatform.presentation.ui.extension.noRippleClickable
import com.example.crowdfundingplatform.presentation.ui.theme.DropdownMenuItemHeight
import com.example.crowdfundingplatform.presentation.ui.theme.LabelBoldStyle
import com.example.crowdfundingplatform.presentation.ui.theme.LabelRegularStyleAlternative
import com.example.crowdfundingplatform.presentation.ui.theme.PaddingExtraLarge
import com.example.crowdfundingplatform.presentation.ui.theme.RoundedCornerShapePercentMedium
import com.example.crowdfundingplatform.presentation.viewmodel.ProjectCreationViewModel

@Composable
fun CategoryDropdownMenu(
    projectCreationViewModel: ProjectCreationViewModel,
    availableCategories: List<ProjectCategory>,
    onDropdownMenuItemClick: (ProjectCategory) -> Unit,
    modifier: Modifier = Modifier
) {
    val projectState by remember { projectCreationViewModel.project }
    var dropdownMenuExpanded by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(DropdownMenuItemHeight)
            .padding(horizontal = PaddingExtraLarge)
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(RoundedCornerShapePercentMedium)
            ),
        contentAlignment = Alignment.Center
    ) {
        val currentCategory = projectState.category
        val currentCategoryStringRes = projectCreationViewModel.getCategoryDescription(currentCategory)

        Text(
            text = if (currentCategoryStringRes != null) stringResource(id = currentCategoryStringRes)
                else stringResource(id = R.string.category),
            style = LabelBoldStyle,
            modifier = Modifier.noRippleClickable {
                dropdownMenuExpanded = true
            }
        )
        DropdownMenu(
            expanded = dropdownMenuExpanded,
            onDismissRequest = {
                dropdownMenuExpanded = false
            }
        ) {
            availableCategories.forEach { category ->
                val categoryStringRes = projectCreationViewModel.getCategoryDescription(category)

                DropdownMenuItem(
                    text = {
                        Text(
                            text = if (categoryStringRes != null) stringResource(id = categoryStringRes)
                                else stringResource(id = R.string.unknownCategory),
                            style = LabelRegularStyleAlternative
                        )
                    },
                    onClick = {
                        onDropdownMenuItemClick(category)
                        dropdownMenuExpanded = false
                    }
                )
            }
        }
    }
}