package com.example.crowdfundingplatform.data.datasource

import com.example.crowdfundingplatform.domain.entity.project.ProjectCategory

object AvailableCategories {

    val categories = listOf(
        ProjectCategory.CINEMA,
        ProjectCategory.ART,
        ProjectCategory.CHARITY,
        ProjectCategory.GAMES,
        ProjectCategory.ECOLOGY_AND_NATURE,
        ProjectCategory.LITERATURE,
        ProjectCategory.MUSIC,
        ProjectCategory.PUBLIC_INITIATIVES,
        ProjectCategory.SPORTS,
        ProjectCategory.TRAVEL
    )

}