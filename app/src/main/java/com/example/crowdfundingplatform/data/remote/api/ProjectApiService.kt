package com.example.crowdfundingplatform.data.remote.api

import com.example.crowdfundingplatform.common.Constants
import com.example.crowdfundingplatform.domain.entity.ProjectCreationRequest
import com.example.crowdfundingplatform.domain.entity.SearchProjectsRequest
import com.example.crowdfundingplatform.domain.entity.SearchProjectsResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ProjectApiService {

    @POST(Constants.PROJECTS_SEARCH_URL)
    suspend fun searchProjects(@Body body: SearchProjectsRequest): SearchProjectsResponse

    @POST(Constants.CREATE_PROJECT_URL)
    suspend fun createProject(
        @Header("Authorization") accessToken: String,
        @Body body: ProjectCreationRequest
    )

}