package com.example.crowdfundingplatform.domain.entity.project

data class ProjectCreationRequest(
    val title: String,
    val summary: String,
    val description: String,
    val targetAmount: Long?,
    val category: ProjectCategory?,
    val finishDate: String,
    val avatarId: String,
    val attachmentIds: List<String>
)