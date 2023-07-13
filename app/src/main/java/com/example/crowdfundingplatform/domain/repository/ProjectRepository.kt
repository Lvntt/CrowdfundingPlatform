package com.example.crowdfundingplatform.domain.repository

import com.example.crowdfundingplatform.domain.entity.ProjectCreationRequest
import com.example.crowdfundingplatform.domain.entity.SearchProjectsRequest
import com.example.crowdfundingplatform.domain.entity.SearchProjectsResponse

interface ProjectRepository {

    suspend fun getAllProjects(body: SearchProjectsRequest): SearchProjectsResponse

    suspend fun createProject(body: ProjectCreationRequest)

}