package com.example.bantuin.model

import kotlinx.serialization.Serializable

@Serializable
data class RegisterResponse(
    val message: String? = null
)