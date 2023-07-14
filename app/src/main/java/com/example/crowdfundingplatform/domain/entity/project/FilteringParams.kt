package com.example.crowdfundingplatform.domain.entity.project

data class FilteringParams(
    val title: String?,
    val category: ProjectCategory?,
    val status: ProjectStatus?
)
