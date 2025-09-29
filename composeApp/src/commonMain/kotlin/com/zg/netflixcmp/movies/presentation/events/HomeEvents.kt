package com.zg.netflixcmp.movies.presentation.events

sealed interface HomeEvents {
    data class NavigateToMovieDetails(val movieId: Int) : HomeEvents
}