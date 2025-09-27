package com.zg.netflixcmp.movies.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zg.netflixcmp.movies.data.repositories.MoviesRepository
import com.zg.netflixcmp.movies.presentation.states.MovieDetailsState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MovieDetailsViewModel(val movieId: Int) : ViewModel() {

    // Repo
    private val movieRepository = MoviesRepository

    // State
    private val _state = MutableStateFlow(MovieDetailsState())
    val state = _state.asStateFlow()

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
}