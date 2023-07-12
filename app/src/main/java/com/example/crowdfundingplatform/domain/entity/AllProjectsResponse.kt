package com.example.crowdfundingplatform.domain.entity

data class AllProjectsResponse(
    val pagingParams: PagingParams,
    val content: List<ProjectSummary>
)