package com.example.crowdfundingplatform.di

import com.example.crowdfundingplatform.common.Constants
import com.example.crowdfundingplatform.data.api.CrowdfundingApiService
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private fun provideRetrofit(
    baseUrl: String
): Retrofit = Retrofit.Builder()
    .baseUrl(baseUrl)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

private fun provideCrowdfundingApiService(): CrowdfundingApiService =
    provideRetrofit(Constants.BASE_URL).create(CrowdfundingApiService::class.java)

fun provideNetworkModule(): Module = module {

    single {
        provideCrowdfundingApiService()
    }

}