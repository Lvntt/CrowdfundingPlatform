package com.example.crowdfundingplatform.domain.entity.project

data class SearchProjectsResponse(
    val pagingParams: PagingParams,
    val content: List<ProjectSummary>
)