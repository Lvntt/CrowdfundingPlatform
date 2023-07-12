package com.example.crowdfundingplatform.domain.entity

data class FilteringParams(
    val title: String?,
    val category: ProjectCategory?,
    val status: ProjectStatus?
)
