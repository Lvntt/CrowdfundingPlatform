package com.example.crowdfundingplatform.domain.entity

data class ProjectSummary(
    val id: String,
    val title: String,
    val summary: String,
    val targetAmount: String,
    val collectedAmount: String,
    val avatarId: String,
    val creationDate: String,
    val finishDate: String,
    val category: ProjectCategory,
    val status: ProjectStatus,
    val authorId: String,
    val isApproved: Boolean
)