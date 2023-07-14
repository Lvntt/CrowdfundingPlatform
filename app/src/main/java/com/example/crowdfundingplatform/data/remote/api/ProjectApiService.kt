package com.example.crowdfundingplatform.data.remote.api

import com.example.crowdfundingplatform.common.Constants
import com.example.crowdfundingplatform.domain.entity.project.ProjectCreationRequest
import com.example.crowdfundingplatform.domain.entity.project.SearchProjectsRequest
import com.example.crowdfundingplatform.domain.entity.project.SearchProjectsResponse
import com.example.crowdfundingplatform.domain.entity.project.ProjectInfo
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import java.math.BigDecimal

interface ProjectApiService {

    @POST(Constants.PROJECTS_SEARCH_URL)
    suspend fun searchProjects(@Body body: SearchProjectsRequest): SearchProjectsResponse

    @POST(Constants.CREATE_PROJECT_URL)
    suspend fun createProject(
        @Header("Authorization") accessToken: String,
        @Body body: ProjectCreationRequest
    )

    @GET(Constants.FULL_PROJECT_INFO_URL)
    suspend fun getFullProjectInfo(@Path("projectId") projectId: String): ProjectInfo

    @POST(Constants.FUND_PROJECT_URL)
    suspend fun fundProject(
        @Header("Authorization") accessToken: String,
        @Path("projectId") projectId: String,
        @Query("money") moneyAmount: BigDecimal
    ): ProjectInfo

}