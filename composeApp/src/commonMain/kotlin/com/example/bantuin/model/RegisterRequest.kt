package com.example.bantuin.model

import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequest(
    val username: String,
    val password: String,
    val email: String,
    val roleName: String,
    val fullName: String,
    val phoneNumber: String,
    val address: String
)