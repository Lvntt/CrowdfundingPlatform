package com.example.crowdfundingplatform.domain.entity

data class SearchProjectsResponse(
    val pagingParams: PagingParams,
    val content: List<ProjectSummary>
)