package com.example.crowdfundingplatform.data.repository

import com.example.crowdfundingplatform.data.remote.api.ProjectApiService
import com.example.crowdfundingplatform.domain.entity.AllProjectsRequest
import com.example.crowdfundingplatform.domain.entity.AllProjectsResponse
import com.example.crowdfundingplatform.domain.repository.ProjectRepository

class ProjectRepositoryImpl(
    private val projectApiService: ProjectApiService
) : ProjectRepository {

    override suspend fun getAllProjects(body: AllProjectsRequest): AllProjectsResponse {
       return projectApiService.getAllProjects(body)
    }

}