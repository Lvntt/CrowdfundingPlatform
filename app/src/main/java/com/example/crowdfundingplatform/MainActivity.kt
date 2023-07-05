package com.example.crowdfundingplatform

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.crowdfundingplatform.presentation.ui.navigation.Navigation
import com.example.crowdfundingplatform.presentation.ui.screen.LoginScreen
import com.example.crowdfundingplatform.presentation.ui.screen.OnboardingScreen
import com.example.crowdfundingplatform.presentation.ui.screen.ProfileInfoScreen
import com.example.crowdfundingplatform.presentation.ui.screen.RegistrationCredentialsScreen
import com.example.crowdfundingplatform.presentation.ui.theme.CrowdfundingPlatformTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CrowdfundingPlatformTheme {
                val navController = rememberNavController()
                val uiController = rememberSystemUiController()
                uiController.setSystemBarsColor(MaterialTheme.colorScheme.background)

                Navigation(navController = navController)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    CrowdfundingPlatformTheme {
        LoginScreen({}, {}, {})
    }
}

@Preview(showBackground = true)
@Composable
private fun OnboardingPreview() {
    CrowdfundingPlatformTheme {
        val uiController = rememberSystemUiController()
        uiController.setSystemBarsColor(MaterialTheme.colorScheme.background)
        OnboardingScreen {}        
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfilePreview() {
    CrowdfundingPlatformTheme {
        val uiController = rememberSystemUiController()
        uiController.setSystemBarsColor(MaterialTheme.colorScheme.background)
        ProfileInfoScreen(
            name = "Placeholder name",
            email = "xdxdxdxdxd@xdxdxd.xd"
        )
    }
}