package com.example.crowdfundingplatform.domain.usecase

import com.example.crowdfundingplatform.domain.entity.ProjectCreationRequest
import com.example.crowdfundingplatform.domain.repository.ProjectRepository

class CreateProjectUseCase(
    private val projectRepository: ProjectRepository
) {

    suspend operator fun invoke(body: ProjectCreationRequest) {
        projectRepository.createProject(body)
    }

}