package com.example.bantuin

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bantuin.model.LoginResponse
import com.example.bantuin.model.RegisterRequest
import com.example.bantuin.model.RegisterResponse
import com.example.bantuin.network.apiService
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupScreen(
    onRegisterSuccess: (RegisterResponse) -> Unit,
    onBackToLogin: () -> Unit
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var roleName by remember { mutableStateOf("") }
    var fullName by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }

    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    var isRoleExpanded by remember { mutableStateOf(false) }
    val roleOptions = listOf("CUSTOMER", "MERCHANT")

    val scope = rememberCoroutineScope()
    val scrollState = rememberScrollState()
    val navyBlue = Color(0xFF1A237E)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(scrollState)
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Bantuin.",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = navyBlue
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "SignUp", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(24.dp))

        // Info Error jika terjadi kendala
        if (errorMessage != null) {
            Text(
                text = errorMessage!!,
                color = Color.Red,
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        // --- FIELD INPUTS ---
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

        Spacer(modifier = Modifier.height(16.dp))

        Column(modifier = Modifier.fillMaxWidth()) {
            Text("Email", fontWeight = FontWeight.SemiBold)
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("email", color = Color.LightGray) },
                shape = RoundedCornerShape(8.dp),
                colors = OutlinedTextFieldDefaults.colors(unfocusedBorderColor = Color(0xFFE0E0E0))
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column(modifier = Modifier.fillMaxWidth()) {
            Text("Role", fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(4.dp))

            ExposedDropdownMenuBox(
                expanded = isRoleExpanded,
                onExpandedChange = { isRoleExpanded = !isRoleExpanded },
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = roleName,
                    onValueChange = {},
                    readOnly = true,
                    modifier = Modifier.fillMaxWidth().menuAnchor(),
                    placeholder = { Text("Pilih Role", color = Color.LightGray) },
                    shape = RoundedCornerShape(8.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Color(0xFFE0E0E0),
                        focusedBorderColor = navyBlue
                    ),
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = isRoleExpanded)
                    }
                )

                ExposedDropdownMenu(
                    expanded = isRoleExpanded,
                    onDismissRequest = { isRoleExpanded = false },
                    modifier = Modifier.background(Color.White)
                ) {
                    roleOptions.forEach { option ->
                        DropdownMenuItem(
                            text = { Text(text = option) },
                            onClick = {
                                roleName = option
                                isRoleExpanded = false
                            },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column(modifier = Modifier.fillMaxWidth()) {
            Text("Full Name", fontWeight = FontWeight.SemiBold)
            OutlinedTextField(
                value = fullName,
                onValueChange = { fullName = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("full name", color = Color.LightGray) },
                shape = RoundedCornerShape(8.dp),
                colors = OutlinedTextFieldDefaults.colors(unfocusedBorderColor = Color(0xFFE0E0E0))
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column(modifier = Modifier.fillMaxWidth()) {
            Text("Phone No.", fontWeight = FontWeight.SemiBold)
            OutlinedTextField(
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("phone number", color = Color.LightGray) },
                shape = RoundedCornerShape(8.dp),
                colors = OutlinedTextFieldDefaults.colors(unfocusedBorderColor = Color(0xFFE0E0E0))
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column(modifier = Modifier.fillMaxWidth()) {
            Text("Address", fontWeight = FontWeight.SemiBold)
            OutlinedTextField(
                value = address,
                onValueChange = { address = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Address", color = Color.LightGray) },
                shape = RoundedCornerShape(8.dp),
                colors = OutlinedTextFieldDefaults.colors(unfocusedBorderColor = Color(0xFFE0E0E0))
            )
        }

        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = {
                if (username.isNotEmpty() && password.isNotEmpty() && email.isNotEmpty() &&
                    roleName.isNotEmpty() && fullName.isNotEmpty() && phoneNumber.isNotEmpty() && address.isNotEmpty()) {

                    isLoading = true
                    errorMessage = null

                    scope.launch {
                        try {
                            val requestData = RegisterRequest(
                                username = username,
                                password = password,
                                email = email,
                                roleName = roleName,
                                fullName = fullName,
                                phoneNumber = phoneNumber,
                                address = address
                            )

                            val response = apiService.register(requestData)
                            onRegisterSuccess(response)

                        } catch (e: Exception) {
                            errorMessage = "Gagal Registrasi: Server tidak merespon atau data sudah terdaftar"
                            println("Register Error: ${e.message}")
                        } finally {
                            isLoading = false
                        }
                    }
                } else {
                    errorMessage = "Harap isi semua kolom tanpa terkecuali"
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
                Text("SignUp", color = Color.White, fontSize = 16.sp)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text("Sudah punya akun? ")
            Text(
                text = "Login",
                color = navyBlue,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable {
                    onBackToLogin()
                }
            )
        }

        Spacer(modifier = Modifier.height(40.dp))
    }
}