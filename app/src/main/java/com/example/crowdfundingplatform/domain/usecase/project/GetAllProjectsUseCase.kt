package com.example.crowdfundingplatform.domain.usecase.project

import com.example.crowdfundingplatform.domain.entity.project.SearchProjectsRequest
import com.example.crowdfundingplatform.domain.entity.project.SearchProjectsResponse
import com.example.crowdfundingplatform.domain.repository.ProjectRepository

class GetAllProjectsUseCase(
    private val projectRepository: ProjectRepository
) {

    suspend operator fun invoke(body: SearchProjectsRequest): SearchProjectsResponse {
        return projectRepository.getAllProjects(body)
    }

}