package com.example.bantuin.utils

import com.russhwolf.settings.Settings


object AuthManager {
    private val settings = Settings()
    private const val KEY_TOKEN = "auth_token"
    private const val KEY_ROLE = "user_role"

    fun saveAuthData(token: String, role: String) {
        settings.putString(KEY_TOKEN, token)
        settings.putString(KEY_ROLE, role)
    }

    fun getToken(): String? {
        val token = settings.getStringOrNull(KEY_TOKEN)
        return if (token.isNullOrEmpty()) null else token
    }

    fun getRole(): String? {
        return settings.getStringOrNull(KEY_ROLE)
    }

    fun clearAuthData() {
        settings.remove(KEY_TOKEN)
        settings.remove(KEY_ROLE)
    }
}