package com.example.crowdfundingplatform.domain.repository

import com.example.crowdfundingplatform.domain.entity.project.ProjectCreationRequest
import com.example.crowdfundingplatform.domain.entity.project.SearchProjectsRequest
import com.example.crowdfundingplatform.domain.entity.project.SearchProjectsResponse

interface ProjectRepository {

    suspend fun getAllProjects(body: SearchProjectsRequest): SearchProjectsResponse

    suspend fun createProject(body: ProjectCreationRequest)

}