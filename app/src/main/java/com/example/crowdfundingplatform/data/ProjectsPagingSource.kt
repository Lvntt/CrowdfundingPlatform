//package com.example.crowdfundingplatform.data
//
//import androidx.paging.PagingSource
//import androidx.paging.PagingState
//import com.example.crowdfundingplatform.domain.entity.ProjectSummary
//import com.example.crowdfundingplatform.domain.repository.ProjectRepository
//import com.example.crowdfundingplatform.domain.repository.UserRepository
//import java.io.IOException
//
//class ProjectsPagingSource(
//    private val projectRepository: ProjectRepository
//) : PagingSource<Int, ProjectSummary>() {
//
//    override fun getRefreshKey(state: PagingState<Int, ProjectSummary>): Int? {
//        return state.anchorPosition
//    }
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProjectSummary> {
//        return try {
//            val page = params.key ?: 1
//
//            val response = projectRepository.getAllProjects()
//            val projects = response.projects
//
//            val prevKey = if (page > 0) page - 1 else null
//            val nextKey = if (response.projects.isNotEmpty()) page + 1 else null
//
//            LoadResult.Page(
//                data = projects,
//                prevKey = prevKey,
//                nextKey = nextKey
//            )
//        } catch (e: IOException) {
//            LoadResult.Error(e)
//        }
//    }
//
//}