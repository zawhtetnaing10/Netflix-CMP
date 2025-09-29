package com.zg.netflixcmp.movies.presentation.actions

sealed interface HomeActions{
    data class OnTapMovie(val movieId : Int) : HomeActions
}