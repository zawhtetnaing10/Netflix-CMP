package com.zg.netflixcmp

import app.cash.turbine.test
import com.zg.netflixcmp.mock.MockMovieRepository
import com.zg.netflixcmp.mock.data.mockMovieDetails
import com.zg.netflixcmp.mock.data.mockMoviesByGenre
import com.zg.netflixcmp.movies.data.repositories.MoviesRepository
import com.zg.netflixcmp.movies.presentation.MovieDetailsViewModel
import com.zg.netflixcmp.movies.presentation.actions.DetailsActions
import com.zg.netflixcmp.movies.presentation.events.DetailsEvents
import com.zg.netflixcmp.movies.presentation.states.MovieDetailsState
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

class MovieDetailsViewModelTest {
    private lateinit var vm: MovieDetailsViewModel
    private lateinit var repo: MoviesRepository

    @OptIn(ExperimentalCoroutinesApi::class)
    @BeforeTest
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        repo = MockMovieRepository()
        vm = MovieDetailsViewModel(movieId = 28, movieRepository = repo)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getDataFromRepo_succeeds() = runTest {
        advanceUntilIdle()

        val currentState = vm.state.value

        val similarMoviesToTest = mockMoviesByGenre[mockMovieDetails.genres?.first()?.id]

        val expectedState = MovieDetailsState(
            movieDetails = mockMovieDetails,
            similarMovies = similarMoviesToTest ?: listOf(),
            isLoading = false,
            error = ""
        )

        assertEquals(expectedState, currentState)
    }

    @Test
    fun onTapBack_navigateBack() = runTest{
        vm.events.test {
            vm.handleAction(DetailsActions.OnTapBack)
            val event = awaitItem()
            assertTrue(event is DetailsEvents.NavigateBack)
        }
    }
}