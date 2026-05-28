package com.example.bantuin.network

import com.example.bantuin.model.LoginRequest
import com.example.bantuin.model.LoginResponse
import com.example.bantuin.model.RegisterRequest
import com.example.bantuin.model.RegisterResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

private val sharedClient = HttpClient {
    install(ContentNegotiation) {
        json(Json {
            ignoreUnknownKeys = true
            isLenient = true
        })
    }
}

val apiService = ApiService(sharedClient)

class ApiService(private val client: HttpClient) {

    private fun buildUrl(path: String): String {
        return "${getBaseUrl()}/$path"
    }

    suspend fun login(request: LoginRequest): LoginResponse {
        return client.post(buildUrl("api/auth/login")) {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }

    suspend fun register(request: RegisterRequest): RegisterResponse {
        return client.post(buildUrl("api/auth/register")) {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }
}