package com.example.crowdfundingplatform.domain.entity

data class AllProjectsResponse(
    val pagingParams: PagingParams,
    val projects: List<ProjectSummary>
)