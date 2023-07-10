package com.example.crowdfundingplatform.data.mock

import com.example.crowdfundingplatform.R
import com.example.crowdfundingplatform.domain.entity.ProjectCategory
import com.example.crowdfundingplatform.domain.entity.ProjectStatus
import com.example.crowdfundingplatform.domain.entity.ProjectSummary

object MockProjectSource {

    val projects = listOf(
        ProjectSummary(
            id = "1",
            title = "New gaming setup",
            summary = "Yo guys! I'm tryna get some cash on a new cool setup for gamers. Please invest, homies, this will really help me! I love frogs btw!",
            collectedAmount = 20000,
            targetAmount = 30000,
            category = ProjectCategory.EXAMPLE_ENUM,
            imageId = "",
            status = ProjectStatus.APPROVED,
            creationDate = "",
            authorId = "",
            imageDrawableId = R.drawable.my_new_gaming_setup
        ),
        ProjectSummary(
            id = "1",
            title = "Frog cameraman",
            summary = "Hi dudes! I'm not sure it's Wednesday, tho, but it's alright. Look at this cool froggo cameraman that I want to hire to help me film my project's trailer! Definitely worth investing",
            collectedAmount = 1500,
            targetAmount = 6969,
            category = ProjectCategory.EXAMPLE_ENUM,
            imageId = "",
            status = ProjectStatus.APPROVED,
            creationDate = "",
            authorId = "",
            imageDrawableId = R.drawable.frog_camerman
        )
    )

}