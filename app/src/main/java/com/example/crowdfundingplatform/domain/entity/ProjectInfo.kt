package com.example.crowdfundingplatform.domain.entity

import java.math.BigDecimal

data class ProjectInfo(
    val id: String,
    val title: String,
    val summary: String,
    val description: String,
    val targetAmount: BigDecimal,
    val collectedAmount: BigDecimal,
    val avatarId: String,
    val creationDate: String,
    val finishDate: String,
    val category: ProjectCategory,
    val status: ProjectStatus,
    val attachmentIds: List<String>,
    val authorId: String,
    val isApproved: Boolean
)
