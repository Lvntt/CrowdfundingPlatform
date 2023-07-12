package com.example.crowdfundingplatform.di

import com.example.crowdfundingplatform.presentation.uistate.CrowdfundingAppState
import com.example.crowdfundingplatform.presentation.viewmodel.AuthViewModel
import com.example.crowdfundingplatform.presentation.viewmodel.DashboardViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

fun providePresentationModule(): Module = module {

    viewModel { (appState: CrowdfundingAppState) ->
        AuthViewModel(
            get(), get(), get(), appState
        )
    }

    viewModel {
        DashboardViewModel(
            get()
        )
    }

}