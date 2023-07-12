package com.example.crowdfundingplatform.data.repository

import com.example.crowdfundingplatform.data.remote.api.ProjectApiService
import com.example.crowdfundingplatform.domain.entity.SearchProjectsRequest
import com.example.crowdfundingplatform.domain.entity.SearchProjectsResponse
import com.example.crowdfundingplatform.domain.repository.ProjectRepository

class ProjectRepositoryImpl(
    private val projectApiService: ProjectApiService
) : ProjectRepository {

    override suspend fun getAllProjects(body: SearchProjectsRequest): SearchProjectsResponse {
       return projectApiService.searchProjects(body)
    }

}