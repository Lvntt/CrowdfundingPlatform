package com.example.crowdfundingplatform.domain.entity

data class SearchProjectsRequest(
    val pagingParams: PagingParams,
    val filteringParams: FilteringParams,
    val sorting: SortingParams
)
