package com.example.crowdfundingplatform.domain.usecase

import com.example.crowdfundingplatform.domain.repository.PaymentRepository

class ActivatePromoCodeUseCase(
    private val paymentRepository: PaymentRepository
) {

    suspend operator fun invoke(promoCode: String) {
        paymentRepository.activatePromoCode(promoCode)
    }

}