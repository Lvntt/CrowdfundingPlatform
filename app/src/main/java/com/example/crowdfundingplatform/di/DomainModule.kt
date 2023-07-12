package com.example.crowdfundingplatform.di

import android.content.Context
import com.example.crowdfundingplatform.data.datasource.TokenDataSource
import com.example.crowdfundingplatform.data.datasource.TokenDataSourceImpl
import com.example.crowdfundingplatform.data.remote.api.AuthApiService
import com.example.crowdfundingplatform.data.remote.api.FileApiService
import com.example.crowdfundingplatform.data.remote.api.ProjectApiService
import com.example.crowdfundingplatform.data.remote.api.UserApiService
import com.example.crowdfundingplatform.data.repository.AuthRepositoryImpl
import com.example.crowdfundingplatform.data.repository.FileRepositoryImpl
import com.example.crowdfundingplatform.data.repository.ProjectRepositoryImpl
import com.example.crowdfundingplatform.data.repository.UserRepositoryImpl
import com.example.crowdfundingplatform.domain.repository.AuthRepository
import com.example.crowdfundingplatform.domain.repository.FileRepository
import com.example.crowdfundingplatform.domain.repository.ProjectRepository
import com.example.crowdfundingplatform.domain.repository.UserRepository
import com.example.crowdfundingplatform.domain.usecase.CheckTokenExistenceUseCase
import com.example.crowdfundingplatform.domain.usecase.GetAllProjectsUseCase
import com.example.crowdfundingplatform.domain.usecase.GetYourProfileUseCase
import com.example.crowdfundingplatform.domain.usecase.LoginUserUseCase
import com.example.crowdfundingplatform.domain.usecase.LogoutUserUseCase
import com.example.crowdfundingplatform.domain.usecase.RefreshTokensUseCase
import com.example.crowdfundingplatform.domain.usecase.RegisterUserUseCase
import com.example.crowdfundingplatform.domain.usecase.UploadFileAndGetIdUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

private fun provideTokenDataSource(
    context: Context
): TokenDataSource = TokenDataSourceImpl(context)

private fun provideAuthRepository(
    authApiService: AuthApiService,
    tokenDataSource: TokenDataSource
): AuthRepository = AuthRepositoryImpl(authApiService, tokenDataSource)

private fun provideUserRepository(
    userApiService: UserApiService,
    tokenDataSource: TokenDataSource
): UserRepository = UserRepositoryImpl(userApiService, tokenDataSource)

private fun provideProjectRepository(
    projectApiService: ProjectApiService
): ProjectRepository = ProjectRepositoryImpl(projectApiService)

private fun provideFileRepository(
    fileApiService: FileApiService,
    context: Context
): FileRepository = FileRepositoryImpl(fileApiService, context.contentResolver)

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

    single {
        provideProjectRepository(get())
    }

    single {
        provideFileRepository(get(), androidContext().applicationContext)
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

    factory {
        CheckTokenExistenceUseCase(get())
    }

    factory {
        GetAllProjectsUseCase(get())
    }

    factory {
        UploadFileAndGetIdUseCase(get())
    }

}