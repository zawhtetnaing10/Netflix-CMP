package com.zg.netflixcmp.redux

import com.zg.netflixcmp.movies.data.vos.GenreVO
import com.zg.netflixcmp.movies.data.vos.MovieVO

data class AppState(
    // Home
    val featuredMovie: MovieVO? = null,
    val moviesByGenre: List<Pair<GenreVO, List<MovieVO>>> = listOf(),
    // Details
    val movieDetails: MovieVO? = null,
    val similarMovies : List<MovieVO> = listOf(),

    // Common
    val isLoading: Boolean = false,
    val error: String = ""
)