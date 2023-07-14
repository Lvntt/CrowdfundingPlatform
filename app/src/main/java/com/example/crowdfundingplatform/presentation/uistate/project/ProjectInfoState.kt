package com.example.crowdfundingplatform.presentation.uistate.project

import androidx.annotation.StringRes
import com.example.crowdfundingplatform.domain.entity.project.ProjectInfo

sealed interface ProjectInfoState {

    object Loading : ProjectInfoState

    data class Error(@StringRes val messageId: Int) : ProjectInfoState

    data class Content(val projectInfo: ProjectInfo) : ProjectInfoState

}