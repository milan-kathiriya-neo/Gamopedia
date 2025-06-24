package org.example.gameopedia

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform