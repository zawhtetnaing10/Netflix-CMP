package com.zg.netflixcmp

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import androidx.room.RoomDatabase
import com.zg.netflixcmp.auth.screens.LoginScreen
import com.zg.netflixcmp.core.data.AppDatabaseProvider
import com.zg.netflixcmp.core.persistence.AppDatabase
import com.zg.netflixcmp.movies.presentation.HomeRoute
import com.zg.netflixcmp.movies.presentation.HomeViewModel
import com.zg.netflixcmp.movies.presentation.MovieDetailsRoute
import com.zg.netflixcmp.movies.presentation.MovieDetailsViewModel
import com.zg.netflixcmp.movies.presentation.events.DetailsEvents
import com.zg.netflixcmp.movies.presentation.events.HomeEvents
import com.zg.netflixcmp.utils.NetflixSansTypography
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(
    databaseBuilder: RoomDatabase.Builder<AppDatabase>
) {

    // Initialize AppDatabase
    AppDatabaseProvider.initializeAppDatabase(databaseBuilder)

    val navController = rememberNavController()

    MaterialTheme(
        typography = NetflixSansTypography()
    ) {
        NavHost(navController, startDestination = AppRoute.Login) {
            composable<AppRoute.Login>(
                enterTransition = { EnterTransition.None },
                exitTransition = { ExitTransition.None },
                popEnterTransition = { EnterTransition.None },
                popExitTransition = { ExitTransition.None }
            ) {
                LoginScreen(onTapLogin = {
                    navController.navigate(AppRoute.Home)
                })
            }

            composable<AppRoute.Home>(
                enterTransition = { EnterTransition.None },
                exitTransition = { ExitTransition.None },
                popEnterTransition = { EnterTransition.None },
                popExitTransition = { ExitTransition.None }
            ) {

                // Initialize View Model
                val homeViewModel = viewModel { HomeViewModel() }

                HomeRoute(
                    viewModel = homeViewModel,
                    onNavigation = { event ->
                        when (event) {
                            is HomeEvents.NavigateToMovieDetails -> {
                                navController.navigate(AppRoute.MovieDetails(event.movieId))
                            }
                        }
                    })
            }

            composable<AppRoute.MovieDetails>(
                enterTransition = { EnterTransition.None },
                exitTransition = { ExitTransition.None },
                popEnterTransition = { EnterTransition.None },
                popExitTransition = { ExitTransition.None }
            ) { backStackEntry ->

                val args = backStackEntry.toRoute<AppRoute.MovieDetails>()

                val detailsViewModel = viewModel { MovieDetailsViewModel(movieId = args.movieId) }

                MovieDetailsRoute(
                    viewModel = detailsViewModel,
                    onNavigation = { event ->
                        when(event){
                            is DetailsEvents.NavigateBack -> {
                                navController.navigateUp()
                            }
                        }
                    })
            }
        }
    }
}