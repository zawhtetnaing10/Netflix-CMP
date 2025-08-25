package com.zg.netflixcmp.movies.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zg.netflixcmp.movies.data.repositories.MoviesRepository
import com.zg.netflixcmp.movies.presentation.states.HomeState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    val movieRepository = MoviesRepository

    // State
    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    init {

        // Featured Movie from DB
        viewModelScope.launch {
            val featuredMovie = movieRepository.getFeaturedMovieFromDB()
            _state.update {
                it.copy(featuredMovie = featuredMovie)
            }
        }

        // Featured Movie
        viewModelScope.launch {
            val featuredMovie = movieRepository.getFeaturedMovie()
            _state.update {
                it.copy(featuredMovie = featuredMovie)
            }
        }

        // Movies by genre
        viewModelScope.launch {
            val moviesByGenre = movieRepository.getMoviesWithFirstFiveGenres()
            _state.update {
                it.copy(moviesByGenre = moviesByGenre)
            }
        }
    }
}