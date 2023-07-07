package com.example.crowdfundingplatform.di

import android.content.Context
import com.example.crowdfundingplatform.data.api.CrowdfundingApiService
import com.example.crowdfundingplatform.data.datasource.TokenDataSource
import com.example.crowdfundingplatform.data.datasource.TokenDataSourceImpl
import com.example.crowdfundingplatform.data.repository.AuthRepositoryImpl
import com.example.crowdfundingplatform.data.repository.UserRepositoryImpl
import com.example.crowdfundingplatform.domain.repository.AuthRepository
import com.example.crowdfundingplatform.domain.repository.UserRepository
import com.example.crowdfundingplatform.domain.usecase.GetYourProfileUseCase
import com.example.crowdfundingplatform.domain.usecase.LoginUserUseCase
import com.example.crowdfundingplatform.domain.usecase.LogoutUserUseCase
import com.example.crowdfundingplatform.domain.usecase.RefreshTokensUseCase
import com.example.crowdfundingplatform.domain.usecase.RegisterUserUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

private fun provideTokenDataSource(
    context: Context
): TokenDataSource = TokenDataSourceImpl(context)

private fun provideAuthRepository(
    crowdfundingApiService: CrowdfundingApiService,
    tokenDataSource: TokenDataSource
): AuthRepository = AuthRepositoryImpl(crowdfundingApiService, tokenDataSource)

private fun provideUserRepository(
    crowdfundingApiService: CrowdfundingApiService,
    tokenDataSource: TokenDataSource
): UserRepository = UserRepositoryImpl(crowdfundingApiService, tokenDataSource)

fun provideDomainModule(): Module = module {

    single {
        provideTokenDataSource(androidContext().applicationContext)
    }

    single {
        provideAuthRepository(get(), get())
    }

    single {
        provideUserRepository(get(), get())
    }

    factory {
        RegisterUserUseCase(get())
    }

    factory {
        LoginUserUseCase(get())
    }

    factory {
        RefreshTokensUseCase(get())
    }

    factory {
        LogoutUserUseCase(get())
    }

    factory {
        GetYourProfileUseCase(get())
    }

}