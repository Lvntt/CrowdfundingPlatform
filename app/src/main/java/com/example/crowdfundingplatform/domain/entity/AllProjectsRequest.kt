package com.example.crowdfundingplatform.domain.entity

data class AllProjectsRequest(
    val pagingParams: PagingParams,
    val filteringParams: FilteringParams,
    val sorting: SortingParams
)
