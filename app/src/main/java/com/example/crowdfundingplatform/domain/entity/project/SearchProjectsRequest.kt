package com.example.crowdfundingplatform.domain.entity.project

data class SearchProjectsRequest(
    val pagingParams: PagingParams,
    val filteringParams: FilteringParams,
    val sorting: SortingParams
)
