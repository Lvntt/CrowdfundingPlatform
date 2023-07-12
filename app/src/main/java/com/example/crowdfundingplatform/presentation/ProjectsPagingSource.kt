package com.example.crowdfundingplatform.presentation

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.crowdfundingplatform.domain.entity.SearchProjectsRequest
import com.example.crowdfundingplatform.domain.entity.FilteringParams
import com.example.crowdfundingplatform.domain.entity.PagingParams
import com.example.crowdfundingplatform.domain.entity.ProjectSummary
import com.example.crowdfundingplatform.domain.entity.SortingParams
import com.example.crowdfundingplatform.domain.entity.SortingType
import com.example.crowdfundingplatform.domain.usecase.GetAllProjectsUseCase
import retrofit2.HttpException
import java.io.IOException

class ProjectsPagingSource(
    private val getAllProjectsUseCase: GetAllProjectsUseCase
) : PagingSource<Int, ProjectSummary>() {

    private var _filteringParams = FilteringParams(
        title = null,
        category = null,
        status = null
    )

    private var _sortingParams = SortingParams(
        title = SortingType.ASC,
        creationDate = SortingType.ASC,
        targetAmount = SortingType.ASC,
        category = SortingType.ASC,
        status = SortingType.ASC,
    )

    fun setFilteringParams(filteringParams: FilteringParams) {
        _filteringParams = filteringParams
    }

    fun setSortingParams(sortingParams: SortingParams) {
        _sortingParams = sortingParams
    }

    override fun getRefreshKey(state: PagingState<Int, ProjectSummary>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProjectSummary> {
        return try {
            val page = params.key ?: 0

            val searchProjectsRequest = SearchProjectsRequest(
                pagingParams = PagingParams(page = page),
                filteringParams = _filteringParams,
                sorting = _sortingParams
            )

            val response = getAllProjectsUseCase(searchProjectsRequest)
            val projects = response.content

            val prevKey = if (page > 0) page - 1 else null
            val nextKey = if (response.content.isNotEmpty()) page + 1 else null

            LoadResult.Page(
                data = projects,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

}