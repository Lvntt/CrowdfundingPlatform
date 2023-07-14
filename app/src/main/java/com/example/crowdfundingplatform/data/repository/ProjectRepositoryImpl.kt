package com.example.crowdfundingplatform.data.repository

import com.example.crowdfundingplatform.data.datasource.TokenDataSource
import com.example.crowdfundingplatform.data.remote.api.ProjectApiService
import com.example.crowdfundingplatform.data.remote.model.TokenType
import com.example.crowdfundingplatform.domain.entity.project.ProjectCreationRequest
import com.example.crowdfundingplatform.domain.entity.project.SearchProjectsRequest
import com.example.crowdfundingplatform.domain.entity.project.SearchProjectsResponse
import com.example.crowdfundingplatform.domain.entity.ProjectInfo
import com.example.crowdfundingplatform.domain.repository.ProjectRepository
import java.math.BigDecimal

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

    override suspend fun getProjectInfo(projectId: String): ProjectInfo {
        return projectApiService.getFullProjectInfo(projectId)
    }

    override suspend fun fundProject(projectId: String, moneyAmount: BigDecimal): ProjectInfo {
        val accessToken = tokenDataSource.fetchToken(TokenType.ACCESS)!!
        return projectApiService.fundProject("Bearer $accessToken", projectId, moneyAmount)
    }

}