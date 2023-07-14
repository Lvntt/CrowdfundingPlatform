package com.example.crowdfundingplatform.domain.usecase

import com.example.crowdfundingplatform.domain.repository.ProjectRepository

class GetProjectInfoUseCase(
    private val projectRepository: ProjectRepository
) {

    suspend operator fun invoke(projectId: String) = projectRepository.getProjectInfo(projectId)

}