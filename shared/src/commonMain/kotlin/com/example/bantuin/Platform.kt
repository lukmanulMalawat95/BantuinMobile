package com.example.bantuin

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform