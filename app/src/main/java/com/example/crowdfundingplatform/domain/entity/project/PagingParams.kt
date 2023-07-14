package com.example.crowdfundingplatform.domain.entity.project

import com.example.crowdfundingplatform.common.Constants

data class PagingParams(
    val totalPages: Int? = null,
    val totalElements: Int? = null,
    val page: Int,
    val size: Int = Constants.DEFAULT_PAGE_SIZE
)
