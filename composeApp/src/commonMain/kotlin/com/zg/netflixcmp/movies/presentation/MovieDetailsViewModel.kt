package com.zg.netflixcmp.movies.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zg.netflixcmp.movies.data.repositories.MoviesRepository
import com.zg.netflixcmp.movies.presentation.states.MovieDetailsState
import com.zg.netflixcmp.redux.AppActions
import com.zg.netflixcmp.redux.AppState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.reduxkotlin.Store
import org.reduxkotlin.StoreSubscription

class MovieDetailsViewModel(val movieId: Int, store: Store<AppState>) : ViewModel() {
    // State
    private val _state = MutableStateFlow(AppState())
    val state = _state.asStateFlow()

    var storeSubscription: StoreSubscription? = null


    init {

        store.dispatch(AppActions.MiddlewareActions.ObserveMovieDetailsFromDb(movieId))
        store.dispatch(AppActions.MiddlewareActions.FetchMovieDetailsAndGetSimilarMovies(movieId))

        storeSubscription = store.subscribe {
            _state.update { store.state }
        }
    }

    override fun onCleared() {
        storeSubscription?.invoke()
        super.onCleared()
    }
}