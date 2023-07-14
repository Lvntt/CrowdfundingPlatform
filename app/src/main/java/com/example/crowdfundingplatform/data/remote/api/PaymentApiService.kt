package com.example.crowdfundingplatform.data.remote.api

import com.example.crowdfundingplatform.common.Constants
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface PaymentApiService {

    @POST("${Constants.PROMOCODE_URL}/{promoCode}")
    suspend fun activatePromoCode(
        @Header("Authorization") accessToken: String,
        @Path("promoCode") promoCode: String
    )

}