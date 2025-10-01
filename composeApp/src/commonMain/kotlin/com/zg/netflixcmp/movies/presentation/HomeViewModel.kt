package com.zg.netflixcmp.movies.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zg.netflixcmp.movies.data.repositories.MoviesRepository
import com.zg.netflixcmp.movies.presentation.states.HomeState
import com.zg.netflixcmp.redux.AppActions
import com.zg.netflixcmp.redux.AppState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.reduxkotlin.Store
import org.reduxkotlin.StoreSubscription

class HomeViewModel(store: Store<AppState>) : ViewModel() {

    // State
    private val _state = MutableStateFlow(AppState())
    val state = _state.asStateFlow()


    var storeSubscription: StoreSubscription? = null

    init {

        store.dispatch(AppActions.MiddlewareActions.FetchFeaturedMovie)
        store.dispatch(AppActions.MiddlewareActions.FetchFeaturedMovieDb)
        store.dispatch(AppActions.MiddlewareActions.FetchMoviesByGenre)

        viewModelScope.launch {
            storeSubscription = store.subscribe {
                println("Getting global state update from Home Screen")
                _state.update { store.state }
            }
        }
    }

    override fun onCleared() {
        // Unsubscribe from further updates
        storeSubscription?.invoke()
        super.onCleared()
    }
}