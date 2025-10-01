package com.zg.netflixcmp.redux

import com.zg.netflixcmp.movies.data.repositories.MoviesRepository
import kotlinx.coroutines.launch
import org.reduxkotlin.Dispatcher
import org.reduxkotlin.Store
import org.reduxkotlin.middleware

val asyncMiddleware =
    middleware { store: Store<AppState>, next: Dispatcher, action: Any ->
        val scope = AppCoroutineScope
        val repository = MoviesRepository
        when (action) {
            AppActions.MiddlewareActions.FetchFeaturedMovie -> {
                scope.ioScope.launch {
                    val firstMovieFromDb = repository.getFeaturedMovie()
                    next(AppActions.ReducerActions.FetchFeaturedMovieSucceeded(firstMovieFromDb))
                }
            }

            AppActions.MiddlewareActions.FetchFeaturedMovieDb -> {
                scope.ioScope.launch {
                    val firstMovieFromDb = repository.getFeaturedMovieFromDb()
                    next(AppActions.ReducerActions.FetchFeaturedMovieDbSucceeded(firstMovieFromDb))
                }
            }

            AppActions.MiddlewareActions.FetchMoviesByGenre -> {
                scope.ioScope.launch {
                    val moviesByGenre = repository.getMoviesWithFirstFiveGenres()
                    next(AppActions.ReducerActions.FetchMoviesByGenreSucceeded(moviesByGenre))
                }
            }

            is AppActions.MiddlewareActions.ObserveMovieDetailsFromDb -> {
                scope.ioScope.launch {
                    val movieDetails = repository.getMoviesByIdFromDb(action.movieId)
                    next(
                        AppActions.ReducerActions.GetMovieDetailsFromDbSucceeded(
                            movieDetails
                        )
                    )
                }
            }

            is AppActions.MiddlewareActions.FetchMovieDetailsAndGetSimilarMovies -> {
                scope.ioScope.launch {
                    val movieDetails = repository.getMovieDetails(action.movieId)

                    movieDetails?.genres?.firstOrNull()?.id?.let { genreId ->
                        val moviesByGenre = repository.getMoviesByGenre(genreId)
                        next(AppActions.ReducerActions.GetSimilarMoviesSucceed(moviesByGenre))
                    } ?: run {
                        next(AppActions.ReducerActions.GetSimilarMoviesSucceed(listOf()))
                    }
                }
            }

            else -> {
                // DO Nothing
            }
        }
    }
