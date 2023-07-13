package com.example.crowdfundingplatform.presentation

import com.example.crowdfundingplatform.R
import com.example.crowdfundingplatform.domain.entity.ProjectCategory

object ProjectCategoryToDescriptionRes {

    val descriptions = mapOf(
        ProjectCategory.CINEMA to R.string.cinemaCategory,
        ProjectCategory.LITERATURE to R.string.literatureCategory,
        ProjectCategory.ART to R.string.artCategory,
        ProjectCategory.GAMES to R.string.gamesCategory,
        ProjectCategory.MUSIC to R.string.musicCategory,
        ProjectCategory.SPORTS to R.string.sportsCategory,
        ProjectCategory.TRAVEL to R.string.travelCategory,
        ProjectCategory.ECOLOGY_AND_NATURE to R.string.ecologyAndNatureCategory,
        ProjectCategory.PUBLIC_INITIATIVES to R.string.publicInitiativesCategory,
        ProjectCategory.CHARITY to R.string.charityCategory,
        ProjectCategory.ALL to R.string.allCategory
    )

}