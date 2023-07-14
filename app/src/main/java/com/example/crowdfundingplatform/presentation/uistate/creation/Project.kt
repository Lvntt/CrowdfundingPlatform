package com.example.crowdfundingplatform.presentation.uistate.creation

import com.example.crowdfundingplatform.common.Constants
import com.example.crowdfundingplatform.domain.entity.project.ProjectCategory

data class Project(
    val title: String = Constants.EMPTY_STRING,
    val summary: String = Constants.EMPTY_STRING,
    val description: String = Constants.EMPTY_STRING,
    val targetAmount: String = Constants.EMPTY_STRING,
    val category: ProjectCategory? = null,
    val finishDate: String = Constants.EMPTY_STRING,
    val avatarId: String = Constants.EMPTY_STRING,
    val attachmentIds: List<String> = listOf()
)