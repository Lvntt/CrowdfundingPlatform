package com.example.crowdfundingplatform.di

import com.example.crowdfundingplatform.common.Constants
import com.example.crowdfundingplatform.data.api.AuthApiService
import com.example.crowdfundingplatform.data.api.ProfileApiService
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

private fun provideAuthApiService(): AuthApiService =
    provideRetrofit(Constants.BASE_URL).create(AuthApiService::class.java)

private fun provideProfileApiService(): ProfileApiService =
    provideRetrofit(Constants.BASE_URL).create(ProfileApiService::class.java)

fun provideNetworkModule(): Module = module {

    single {
        provideAuthApiService()
    }

    single {
        provideProfileApiService()
    }

}