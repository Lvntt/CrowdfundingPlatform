package com.example.crowdfundingplatform.domain.usecase.project

import com.example.crowdfundingplatform.domain.entity.project.ProjectCreationRequest
import com.example.crowdfundingplatform.domain.repository.ProjectRepository

class CreateProjectUseCase(
    private val projectRepository: ProjectRepository
) {

    suspend operator fun invoke(body: ProjectCreationRequest) {
        projectRepository.createProject(body)
    }

}