package com.example.crowdfundingplatform.domain.entity

data class PagingParams(
    val page: Int,
    val size: Int,
    val totalPages: Int?
)
