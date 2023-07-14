package com.example.crowdfundingplatform.di

import com.example.crowdfundingplatform.common.Constants
import com.example.crowdfundingplatform.data.remote.api.AuthApiService
import com.example.crowdfundingplatform.data.remote.api.FileApiService
import com.example.crowdfundingplatform.data.remote.api.PaymentApiService
import com.example.crowdfundingplatform.data.remote.api.ProjectApiService
import com.example.crowdfundingplatform.data.remote.api.UserApiService
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

private fun provideProjectApiService(): ProjectApiService =
    provideRetrofit(Constants.BASE_URL).create(ProjectApiService::class.java)

private fun provideUserApiService(): UserApiService =
    provideRetrofit(Constants.BASE_URL).create(UserApiService::class.java)

private fun provideFileApiService(): FileApiService =
    provideRetrofit(Constants.BASE_URL).create(FileApiService::class.java)

private fun providePaymentApiService(): PaymentApiService =
    provideRetrofit(Constants.BASE_URL).create(PaymentApiService::class.java)

fun provideNetworkModule(): Module = module {

    single {
        provideAuthApiService()
    }

    single {
        provideProjectApiService()
    }

    single {
        provideUserApiService()
    }

    single {
        provideFileApiService()
    }

    single {
        providePaymentApiService()
    }

}