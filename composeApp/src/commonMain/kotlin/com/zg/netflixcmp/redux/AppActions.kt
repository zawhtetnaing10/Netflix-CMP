package com.zg.netflixcmp.redux

import com.zg.netflixcmp.movies.data.vos.GenreVO
import com.zg.netflixcmp.movies.data.vos.MovieVO

sealed interface AppActions {
    // Actions for Middleware
    sealed interface MiddlewareActions {
        // Home
        object FetchFeaturedMovie : MiddlewareActions
        object FetchFeaturedMovieDb : MiddlewareActions
        object FetchMoviesByGenre : MiddlewareActions

        // Details
        data class ObserveMovieDetailsFromDb(val movieId: Int) :
            MiddlewareActions

        data class FetchMovieDetailsAndGetSimilarMovies(val movieId: Int) : MiddlewareActions
    }

    // Actions for Reducer
    sealed interface ReducerActions {
        // Home
        data class FetchFeaturedMovieSucceeded(val featuredMovie: MovieVO?) : ReducerActions
        data class FetchFeaturedMovieDbSucceeded(val featuredMovieDb: MovieVO?) : ReducerActions
        data class FetchMoviesByGenreSucceeded(val moviesByGenre: List<Pair<GenreVO, List<MovieVO>>>) :
            ReducerActions

        // Details
        data class GetMovieDetailsFromDbSucceeded(val movieDetails: MovieVO?) : ReducerActions
        data class GetSimilarMoviesSucceed(val similarMovies: List<MovieVO>) : ReducerActions
    }
}