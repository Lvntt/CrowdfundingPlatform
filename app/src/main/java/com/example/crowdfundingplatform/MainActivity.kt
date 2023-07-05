package com.example.crowdfundingplatform

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.crowdfundingplatform.presentation.ui.screen.LoginScreen
import com.example.crowdfundingplatform.presentation.ui.screen.OnboardingScreen
import com.example.crowdfundingplatform.presentation.ui.screen.RegistrationCredentialsScreen
import com.example.crowdfundingplatform.presentation.ui.theme.CrowdfundingPlatformTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CrowdfundingPlatformTheme {
                RegistrationCredentialsScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    CrowdfundingPlatformTheme {
        LoginScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingPreview() {
    CrowdfundingPlatformTheme {
        OnboardingScreen()
    }
}