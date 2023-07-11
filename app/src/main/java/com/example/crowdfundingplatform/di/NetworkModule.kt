package com.example.crowdfundingplatform.di

import com.example.crowdfundingplatform.common.Constants
import com.example.crowdfundingplatform.data.remote.api.AuthApiService
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

private fun provideCrowdfundingApiService(): AuthApiService =
    provideRetrofit(Constants.BASE_URL).create(AuthApiService::class.java)

fun provideNetworkModule(): Module = module {

    single {
        provideCrowdfundingApiService()
    }

}