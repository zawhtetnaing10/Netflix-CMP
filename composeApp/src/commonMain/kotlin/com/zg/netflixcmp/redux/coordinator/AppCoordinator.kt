package com.zg.netflixcmp.redux.coordinator

interface AppCoordinator {
    fun navigateToHome()
    fun navigateToMovieDetails(movieId: Int)
    fun navigateBack()
}