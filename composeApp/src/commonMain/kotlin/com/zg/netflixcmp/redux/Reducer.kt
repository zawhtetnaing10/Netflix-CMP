package com.zg.netflixcmp.redux

import org.reduxkotlin.Reducer

val reducer: Reducer<AppState> = { state, action ->
    when (action) {
        is AppActions.ReducerActions.FetchFeaturedMovieSucceeded -> {
            state.copy(featuredMovie = action.featuredMovie)
        }

        is AppActions.ReducerActions.FetchFeaturedMovieDbSucceeded -> {
            state.copy(featuredMovie = action.featuredMovieDb)
        }

        is AppActions.ReducerActions.FetchMoviesByGenreSucceeded -> {
            state.copy(moviesByGenre = action.moviesByGenre)
        }

        is AppActions.ReducerActions.GetMovieDetailsFromDbSucceeded -> {
            state.copy(movieDetails = action.movieDetails)
        }

        is AppActions.ReducerActions.GetSimilarMoviesSucceed -> {
            state.copy(similarMovies = action.similarMovies)
        }

        else -> state
    }
}