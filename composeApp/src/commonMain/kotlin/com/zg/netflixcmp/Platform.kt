package com.zg.netflixcmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform