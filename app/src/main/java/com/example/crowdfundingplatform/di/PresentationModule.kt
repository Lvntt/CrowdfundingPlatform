package com.example.crowdfundingplatform.di

import com.example.crowdfundingplatform.presentation.viewmodel.AuthViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

fun providePresentationModule(): Module = module {
    viewModel {
        AuthViewModel(
            get(), get()
        )
    }
}