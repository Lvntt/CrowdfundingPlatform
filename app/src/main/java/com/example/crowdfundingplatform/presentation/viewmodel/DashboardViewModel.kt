package com.example.crowdfundingplatform.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.crowdfundingplatform.common.Constants
import com.example.crowdfundingplatform.domain.entity.ProjectSummary
import com.example.crowdfundingplatform.domain.usecase.GetAllProjectsUseCase
import com.example.crowdfundingplatform.presentation.ProjectsPagingSource
import kotlinx.coroutines.flow.Flow

class DashboardViewModel(
    private val getAllProjectsUseCase: GetAllProjectsUseCase
) : ViewModel() {

    val projects: Flow<PagingData<ProjectSummary>> = Pager(PagingConfig(pageSize = Constants.DEFAULT_PAGE_SIZE)) {
        ProjectsPagingSource(getAllProjectsUseCase)
    }.flow.cachedIn(viewModelScope)

}