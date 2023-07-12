package com.example.crowdfundingplatform.domain.usecase

import com.example.crowdfundingplatform.domain.entity.SearchProjectsRequest
import com.example.crowdfundingplatform.domain.entity.SearchProjectsResponse
import com.example.crowdfundingplatform.domain.repository.ProjectRepository

class GetAllProjectsUseCase(
    private val projectRepository: ProjectRepository
) {

    suspend operator fun invoke(body: SearchProjectsRequest): SearchProjectsResponse {
        return projectRepository.getAllProjects(body)
    }

}