package com.example.crowdfundingplatform

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.crowdfundingplatform.presentation.ui.screen.RegistrationPersonalInfoScreen
import com.example.crowdfundingplatform.presentation.ui.screen.OnboardingScreen
import com.example.crowdfundingplatform.presentation.ui.theme.CrowdfundingPlatformTheme
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CrowdfundingPlatformTheme {
                RegistrationPersonalInfoScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingPreview() {
    CrowdfundingPlatformTheme {
        OnboardingScreen()
    }
}