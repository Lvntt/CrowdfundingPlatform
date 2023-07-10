package com.example.crowdfundingplatform

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.crowdfundingplatform.presentation.ui.CrowdfundingApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CrowdfundingApp()
        }
    }
}