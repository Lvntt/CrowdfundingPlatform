package com.example.crowdfundingplatform.data.remote.api

import com.example.crowdfundingplatform.common.Constants
import com.example.crowdfundingplatform.domain.entity.AllProjectsRequest
import com.example.crowdfundingplatform.domain.entity.AllProjectsResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ProjectApiService {

    @POST(Constants.ALL_PROJECTS_URL)
    suspend fun getAllProjects(@Body body: AllProjectsRequest): AllProjectsResponse

}