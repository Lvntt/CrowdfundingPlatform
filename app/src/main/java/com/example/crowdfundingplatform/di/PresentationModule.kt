package com.example.crowdfundingplatform.di

import com.example.crowdfundingplatform.presentation.viewmodel.AuthViewModel
import com.example.crowdfundingplatform.presentation.viewmodel.DashboardViewModel
import com.example.crowdfundingplatform.presentation.viewmodel.EditPersonalInfoViewModel
import com.example.crowdfundingplatform.presentation.viewmodel.OnboardingViewModel
import com.example.crowdfundingplatform.presentation.viewmodel.ProfileInfoViewModel
import com.example.crowdfundingplatform.presentation.viewmodel.ProjectCreationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

fun providePresentationModule(): Module = module {

    viewModel {
        AuthViewModel(
            get(), get()
        )
    }

    viewModel {
        OnboardingViewModel(
            get()
        )
    }

    viewModel {
        ProfileInfoViewModel(
            get(), get()
        )
    }

    viewModel {
        DashboardViewModel(
            get()
        )
    }

    viewModel {
        ProjectCreationViewModel(
            get(), get(), get()
        )
    }
    
    viewModel {
        EditPersonalInfoViewModel(
            get(), get(), get()
        )
    }

}