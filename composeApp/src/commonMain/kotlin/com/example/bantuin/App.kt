package com.example.bantuin

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.resources.painterResource

import bantuin.composeapp.generated.resources.Res
import bantuin.composeapp.generated.resources.compose_multiplatform
import com.example.bantuin.utils.AuthManager
import com.namaapp.ui.login.LoginScreen

@Composable
@Preview
fun App() {
    var currentScreen by remember { mutableStateOf("splash") }

    MaterialTheme {
        when (currentScreen) {
            "splash" -> {
                SplashScreen(
                    onTokenValid = { token ->
                        currentScreen = "home"
                    },
                    onTokenInvalid = {
                        currentScreen = "login"
                    }
                )
            }
            "login" -> {
                LoginScreen(
                    onLoginSuccess = { response ->
                        currentScreen = "home"
                    },
                    onNavigateToSignup = {
                        currentScreen = "signup"
                    }
                )
            }
            "signup" -> {
                SignupScreen(
                    onRegisterSuccess = {
                        currentScreen = "login"
                    },
                    onBackToLogin = {
                        currentScreen = "login"
                    }
                )
            }
            "home" -> {
                HomeScreen(
                    onLogoutSuccess = {
                        AuthManager.clearAuthData()
                        currentScreen = "login"
                    }
                )
            }
            "profile" -> {
                ProfileScreen(
                    onLogoutClick = {
                        AuthManager.clearAuthData()
                        currentScreen = "login"
                    },
                    onAddVehicleClick = {
                        currentScreen = "add_vehicle"
                    },
                    onHistoryClick = {
                        currentScreen = "repair_history"
                    }
                )
            }
        }
    }
}