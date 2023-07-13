package com.example.crowdfundingplatform.di

import com.example.crowdfundingplatform.data.converter.UserConverter
import org.koin.core.module.Module
import org.koin.dsl.module

private fun provideUserConverter(): UserConverter = UserConverter()

fun provideDataModule(): Module = module {
    factory {
        provideUserConverter()
    }
}