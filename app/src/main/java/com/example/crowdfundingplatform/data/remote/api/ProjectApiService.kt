package com.example.crowdfundingplatform.data.remote.api

import com.example.crowdfundingplatform.common.Constants
import com.example.crowdfundingplatform.domain.entity.AllProjectsRequest
import com.example.crowdfundingplatform.domain.entity.AllProjectsResponse
import retrofit2.http.Body
import retrofit2.http.GET

// TODO di
interface ProjectApiService {

    @GET(Constants.ALL_PROJECTS_URL)
    suspend fun getAllProjects(@Body body: AllProjectsRequest): AllProjectsResponse

}