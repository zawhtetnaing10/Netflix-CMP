package com.zg.netflixcmp.movies.presentation.states

import com.zg.netflixcmp.movies.data.vos.MovieVO

data class MovieDetailsState(
    val movieDetails: MovieVO? = null,
    val similarMovies : List<MovieVO> = listOf(),
    val isLoading: Boolean = false,
    val error: String = ""
)