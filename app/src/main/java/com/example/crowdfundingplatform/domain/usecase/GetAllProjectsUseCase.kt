package com.example.crowdfundingplatform.domain.usecase

import com.example.crowdfundingplatform.domain.entity.AllProjectsRequest
import com.example.crowdfundingplatform.domain.entity.AllProjectsResponse
import com.example.crowdfundingplatform.domain.repository.ProjectRepository

class GetAllProjectsUseCase(
    private val projectRepository: ProjectRepository
) {

    suspend operator fun invoke(body: AllProjectsRequest): AllProjectsResponse {
        return projectRepository.getAllProjects(body)
    }

}