package com.namaapp.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bantuin.model.LoginRequest
import com.example.bantuin.model.LoginResponse
import com.example.bantuin.network.apiService
import com.example.bantuin.utils.AuthManager
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource

@Composable
fun LoginScreen(onLoginSuccess: (LoginResponse) -> Unit,
                onNavigateToSignup: () -> Unit) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val scope = rememberCoroutineScope()
    val navyBlue = Color(0xFF1A237E)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(60.dp))

        Text(
            text = "Bantuin.",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = navyBlue
        )

        Spacer(modifier = Modifier.height(24.dp))
        Text(text = "Login", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(32.dp))

        Column(modifier = Modifier.fillMaxWidth()) {
            Text("Username", fontWeight = FontWeight.SemiBold)
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("username", color = Color.LightGray) },
                shape = RoundedCornerShape(8.dp),
                colors = OutlinedTextFieldDefaults.colors(unfocusedBorderColor = Color(0xFFE0E0E0))
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column(modifier = Modifier.fillMaxWidth()) {
            Text("Password", fontWeight = FontWeight.SemiBold)
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
                shape = RoundedCornerShape(8.dp),
                colors = OutlinedTextFieldDefaults.colors(unfocusedBorderColor = Color(0xFFE0E0E0))
            )
        }

        Text(
            text = "Forgot Password ?",
            modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
            textAlign = TextAlign.Start,
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (username.isNotEmpty() && password.isNotEmpty()) {
                    isLoading = true
                    errorMessage = null

                    scope.launch {
                        try {
                            val response = apiService.login(LoginRequest(username, password))
                            AuthManager.saveAuthData(
                                token = response.token,
                                role = response.role
                            )

                            onLoginSuccess(response)
                        } catch (e: Exception) {
                            errorMessage = "Gagal Login: Username/Password salah atau server tidak terjangkau"
                            println(errorMessage)
                        } finally {
                            isLoading = false
                        }
                    }
                } else {
                    errorMessage = "Harap isi semua kolom"
                }
            },
            modifier = Modifier.fillMaxWidth().height(50.dp),
            enabled = !isLoading,
            colors = ButtonDefaults.buttonColors(containerColor = navyBlue),
            shape = RoundedCornerShape(8.dp)
        ) {
            if (isLoading) {
                CircularProgressIndicator(color = Color.White, modifier = Modifier.size(24.dp))
            } else {
                Text("Login", color = Color.White, fontSize = 16.sp)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            HorizontalDivider(modifier = Modifier.weight(1f), color = Color(0xFFE0E0E0))
            Text(" or ", modifier = Modifier.padding(horizontal = 8.dp), color = Color.Gray)
            HorizontalDivider(modifier = Modifier.weight(1f), color = Color(0xFFE0E0E0))
        }

        Spacer(modifier = Modifier.height(24.dp))

        SocialButton(text = "Continue with Gmail", icon = null)
        Spacer(modifier = Modifier.height(12.dp))
        SocialButton(text = "Continue with Facebook", icon = null)

        Spacer(modifier = Modifier.height(24.dp))

        Row {
            Text("Don't have any account ? ")
            Text(
                text = "Signup",
                color = navyBlue,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable {
                    onNavigateToSignup()
                }
            )
        }

        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
fun SocialButton(text: String, icon: Any?) {
    OutlinedButton(
        onClick = { },
        modifier = Modifier.fillMaxWidth().height(50.dp),
        shape = RoundedCornerShape(8.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE0E0E0))
    ) {
        Text(text, color = Color.Black)
    }
}