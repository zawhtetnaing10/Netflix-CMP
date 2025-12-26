package com.zg.netflixcmp

import app.cash.turbine.test
import com.zg.netflixcmp.mock.MockMovieRepository
import com.zg.netflixcmp.mock.data.mockGenres
import com.zg.netflixcmp.mock.data.mockMoviesByGenre
import com.zg.netflixcmp.mock.data.mockNowPlayingMovies
import com.zg.netflixcmp.movies.data.repositories.MoviesRepository
import com.zg.netflixcmp.movies.data.vos.GenreVO
import com.zg.netflixcmp.movies.data.vos.MovieVO
import com.zg.netflixcmp.movies.presentation.HomeViewModel
import com.zg.netflixcmp.movies.presentation.actions.HomeActions
import com.zg.netflixcmp.movies.presentation.events.HomeEvents
import com.zg.netflixcmp.movies.presentation.states.HomeState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class HomeViewModelTest {
    private lateinit var vm: HomeViewModel
    private lateinit var repo: MoviesRepository

    @OptIn(ExperimentalCoroutinesApi::class)
    @BeforeTest
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        repo = MockMovieRepository()
        vm = HomeViewModel(movieRepository = repo)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getDataFromRepo_succeeds() = runTest {

        advanceUntilIdle()

        val currentState = vm.state.value

        val moviesByGenreToCompare: MutableList<Pair<GenreVO, List<MovieVO>>> = mutableListOf()
        mockGenres.take(5).forEach {
            val moviesByGenre = mockMoviesByGenre[it.id] ?: listOf()
            moviesByGenreToCompare.add(Pair(it, moviesByGenre))
        }

        val stateToCompare = HomeState(
            featuredMovie = mockNowPlayingMovies.first(),
            moviesByGenre = moviesByGenreToCompare,
            isLoading = false,
            error = ""
        )
        assertEquals(stateToCompare, currentState)
    }

    @Test
    fun onTapMovie_navigateToMovieDetails() = runTest {
        vm.events.test {
            vm.handleAction(HomeActions.OnTapMovie(28))
            val event = awaitItem()

            assertTrue(event is HomeEvents.NavigateToMovieDetails)
            assertEquals(28, event.movieId)
        }
    }
}