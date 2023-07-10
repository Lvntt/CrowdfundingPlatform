package com.example.crowdfundingplatform.domain.entity

data class ProjectSummary(
    val id: String,
    val title: String,
    val summary: String,
    val collectedAmount: Int,
    val targetAmount: Int,
    val category: ProjectCategory,
    val imageId: String,
    val status: ProjectStatus,
    val creationDate: String,
    val authorId: String,
    // TODO TEMPORARY FOR MOCKING
    val imageDrawableId: Int
)