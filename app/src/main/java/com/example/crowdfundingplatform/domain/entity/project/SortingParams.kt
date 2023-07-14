package com.example.crowdfundingplatform.domain.entity.project

data class SortingParams(
    val title: SortingType,
    val creationDate: SortingType,
    val targetAmount: SortingType,
    val category: SortingType,
    val status: SortingType
)
