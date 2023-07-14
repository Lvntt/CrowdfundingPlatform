package com.example.crowdfundingplatform.domain.usecase.project

import com.example.crowdfundingplatform.domain.repository.ProjectRepository
import java.math.BigDecimal

class FundProjectUseCase(
    private val projectRepository: ProjectRepository
) {

    suspend operator fun invoke(projectId: String, moneyAmount: BigDecimal) =
        projectRepository.fundProject(projectId, moneyAmount)

}