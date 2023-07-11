package com.example.crowdfundingplatform.domain.repository

import com.example.crowdfundingplatform.domain.entity.AllProjectsRequest
import com.example.crowdfundingplatform.domain.entity.AllProjectsResponse

interface ProjectRepository {

    suspend fun getAllProjects(body: AllProjectsRequest): AllProjectsResponse

}