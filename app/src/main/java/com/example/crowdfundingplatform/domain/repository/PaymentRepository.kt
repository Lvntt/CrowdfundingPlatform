package com.example.crowdfundingplatform.domain.repository

interface PaymentRepository {

    suspend fun activatePromoCode(promoCode: String)

}