package com.example.crowdfundingplatform.data.repository

import com.example.crowdfundingplatform.data.datasource.TokenDataSource
import com.example.crowdfundingplatform.data.remote.api.ProjectApiService
import com.example.crowdfundingplatform.data.remote.model.TokenType
import com.example.crowdfundingplatform.domain.entity.ProjectCreationRequest
import com.example.crowdfundingplatform.domain.entity.SearchProjectsRequest
import com.example.crowdfundingplatform.domain.entity.SearchProjectsResponse
import com.example.crowdfundingplatform.domain.repository.ProjectRepository

class ProjectRepositoryImpl(
    private val projectApiService: ProjectApiService,
    private val tokenDataSource: TokenDataSource
) : ProjectRepository {

    override suspend fun getAllProjects(body: SearchProjectsRequest): SearchProjectsResponse {
       return projectApiService.searchProjects(body)
    }

    override suspend fun createProject(body: ProjectCreationRequest) {
        val accessToken = tokenDataSource.fetchToken(TokenType.ACCESS)!!
        return projectApiService.createProject("Bearer $accessToken", body)
    }

}