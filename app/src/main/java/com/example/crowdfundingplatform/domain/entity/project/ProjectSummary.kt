package com.example.crowdfundingplatform.domain.entity.project

import java.math.BigDecimal

data class ProjectSummary(
    val id: String,
    val title: String,
    val summary: String,
    val targetAmount: BigDecimal,
    val collectedAmount: BigDecimal,
    val avatarId: String,
    val creationDate: String,
    val finishDate: String,
    val category: ProjectCategory,
    val status: ProjectStatus,
    val authorId: String,
    val isApproved: Boolean
)