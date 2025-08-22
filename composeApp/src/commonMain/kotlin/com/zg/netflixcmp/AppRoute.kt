package com.zg.netflixcmp

import kotlinx.serialization.Serializable

@Serializable
sealed class AppRoute {
    @Serializable
    data object Login : AppRoute()

    @Serializable
    data object Home : AppRoute()

    @Serializable
    data class MovieDetails(val movieId: Int) : AppRoute()
}