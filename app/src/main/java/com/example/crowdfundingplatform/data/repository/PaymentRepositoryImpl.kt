package com.example.crowdfundingplatform.data.repository

import com.example.crowdfundingplatform.data.datasource.TokenDataSource
import com.example.crowdfundingplatform.data.remote.api.PaymentApiService
import com.example.crowdfundingplatform.data.remote.model.TokenType
import com.example.crowdfundingplatform.domain.repository.PaymentRepository

class PaymentRepositoryImpl(
    private val paymentApiService: PaymentApiService,
    private val tokenDataSource: TokenDataSource
) : PaymentRepository {

    override suspend fun activatePromoCode(promoCode: String) {
        val accessToken = tokenDataSource.fetchToken(TokenType.ACCESS)!!
        paymentApiService.activatePromoCode("Bearer $accessToken", promoCode)
    }

}