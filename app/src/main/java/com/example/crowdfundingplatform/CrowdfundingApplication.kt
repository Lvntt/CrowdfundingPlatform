package com.example.crowdfundingplatform

import android.app.Application
import com.example.crowdfundingplatform.di.provideDataModule
import com.example.crowdfundingplatform.di.provideDomainModule
import com.example.crowdfundingplatform.di.provideNetworkModule
import com.example.crowdfundingplatform.di.providePresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CrowdfundingApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CrowdfundingApplication)
            modules(
                provideDomainModule(),
                provideNetworkModule(),
                providePresentationModule(),
                provideDataModule()
            )
        }
    }

}