package com.zg.netflixcmp.movies.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zg.netflixcmp.movies.data.repositories.MoviesRepository
import com.zg.netflixcmp.movies.presentation.actions.HomeActions
import com.zg.netflixcmp.movies.presentation.events.HomeEvents
import com.zg.netflixcmp.movies.presentation.states.HomeState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val movieRepository : MoviesRepository
) : ViewModel() {

    // State
    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    // Events
    private val _events = MutableSharedFlow<HomeEvents>()
    val events = _events.asSharedFlow()

    init {

        // Get First Movie from db and show it first as featured movie
        viewModelScope.launch {
            val firstMovieFromDb = movieRepository.getFeaturedMovieFromDb()
            _state.update {
                it.copy(featuredMovie = firstMovieFromDb)
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

    // Handle Action
    fun handleAction(action: HomeActions) {
        when (action) {
            // Other Actions
            is HomeActions.OnTapMovie -> {
                viewModelScope.launch {
                    _events.emit(HomeEvents.NavigateToMovieDetails(action.movieId))
                }
            }
        }
    }
}