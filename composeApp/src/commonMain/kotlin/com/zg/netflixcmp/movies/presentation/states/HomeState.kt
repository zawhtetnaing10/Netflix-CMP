package com.zg.netflixcmp.movies.presentation.states

import com.zg.netflixcmp.movies.data.vos.GenreVO
import com.zg.netflixcmp.movies.data.vos.MovieVO

data class HomeState(
    val featuredMovie: MovieVO? = null,
    val moviesByGenre: List<Pair<GenreVO, List<MovieVO>>> = listOf(),
    val isLoading: Boolean = false,
    val error: String = "",
)