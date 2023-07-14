package com.example.crowdfundingplatform.domain.repository

import com.example.crowdfundingplatform.domain.entity.ProjectCreationRequest
import com.example.crowdfundingplatform.domain.entity.ProjectInfo
import com.example.crowdfundingplatform.domain.entity.SearchProjectsRequest
import com.example.crowdfundingplatform.domain.entity.SearchProjectsResponse
import java.math.BigDecimal

interface ProjectRepository {

    suspend fun getAllProjects(body: SearchProjectsRequest): SearchProjectsResponse

    suspend fun createProject(body: ProjectCreationRequest)

    suspend fun getProjectInfo(projectId: String): ProjectInfo

    suspend fun fundProject(projectId: String, moneyAmount: BigDecimal): ProjectInfo

}