package com.zg.netflixcmp.redux.coordinator

import androidx.navigation.NavHostController
import com.zg.netflixcmp.AppRoute

// Coordinator
class ComposeAppCoordinator(
    val navController: NavHostController
) : AppCoordinator {
    override fun navigateToHome() {
        navController.navigate(AppRoute.Home)
    }

    override fun navigateToMovieDetails(movieId: Int) {
        navController.navigate(AppRoute.MovieDetails(movieId))
    }

    override fun navigateBack() {
        navController.navigateUp()
    }
}