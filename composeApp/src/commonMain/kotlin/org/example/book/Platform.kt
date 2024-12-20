package org.example.book

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform