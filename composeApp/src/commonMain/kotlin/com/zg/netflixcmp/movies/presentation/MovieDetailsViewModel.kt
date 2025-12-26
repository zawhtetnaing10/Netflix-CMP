package com.zg.netflixcmp.movies.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zg.netflixcmp.movies.data.repositories.MoviesRepository
import com.zg.netflixcmp.movies.data.repositories.MoviesRepositoryImpl
import com.zg.netflixcmp.movies.presentation.actions.DetailsActions
import com.zg.netflixcmp.movies.presentation.events.DetailsEvents
import com.zg.netflixcmp.movies.presentation.states.MovieDetailsState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MovieDetailsViewModel(val movieId: Int, private val movieRepository : MoviesRepository) : ViewModel() {
    // State
    private val _state = MutableStateFlow(MovieDetailsState())
    val state = _state.asStateFlow()

    // Events
    private val _events = MutableSharedFlow<DetailsEvents>()
    val events = _events.asSharedFlow()

    init {

        // Get data from db and show it first
        viewModelScope.launch {
            movieRepository.getMoviesByIdFromDb(movieId)
                .collect { movieDetails ->
                    _state.update { it.copy(movieDetails = movieDetails) }
                }
        }

        viewModelScope.launch {
            val movieDetails = movieRepository.getMovieDetails(movieId)

            // Stop the state update and let persistence layer handle this.
            //_state.update { it.copy(movieDetails = movieDetails) }

            movieDetails?.genres?.let {
                viewModelScope.launch {
                    val similarMovies =
                        movieRepository.getMoviesByGenre(movieDetails.genres.first().id)
                    _state.update { it.copy(similarMovies = similarMovies) }
                }
            }
        }
    }

    // Handle Action
    fun handleAction(action: DetailsActions) {
        when (action) {
            is DetailsActions.OnTapBack -> {
                viewModelScope.launch {
                    _events.emit(DetailsEvents.NavigateBack)
                }
            }
        }
    }
}