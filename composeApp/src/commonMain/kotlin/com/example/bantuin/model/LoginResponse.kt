package com.example.bantuin.model

import kotlinx.serialization.Serializable
@Serializable
data class LoginResponse(
    val message: String,
    val username: String,
    val role: String,
    val token: String
)