package com.example.bantuin

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import bantuin.composeapp.generated.resources.Res
import bantuin.composeapp.generated.resources.compose_multiplatform
import com.example.bantuin.utils.AuthManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource

@Composable
fun SplashScreen(
    onTokenValid: (String) -> Unit,
    onTokenInvalid: () -> Unit
) {
    LaunchedEffect(Unit) {
        delay(2000)

        val savedToken = AuthManager.getToken()

        if (savedToken != null) {
            onTokenValid(savedToken)
        } else {
            onTokenInvalid()
        }
    }

    Box(
        modifier = Modifier.fillMaxSize().background(Color(0xFF1A237E)),
        contentAlignment = Alignment.Center
    ) {
        Text("Bantuin.", color = Color.White, fontSize = 40.sp, fontWeight = FontWeight.Bold)
    }
}