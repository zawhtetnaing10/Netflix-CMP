package com.zg.netflixcmp

import com.zg.netflixcmp.mock.MockMovieApiService
import com.zg.netflixcmp.mock.MockMovieDao
import com.zg.netflixcmp.mock.data.mockGenres
import com.zg.netflixcmp.mock.data.mockMovieDetails
import com.zg.netflixcmp.mock.data.mockMoviesByGenre
import com.zg.netflixcmp.mock.data.mockNowPlayingMovies
import com.zg.netflixcmp.movies.data.repositories.MoviesRepository
import com.zg.netflixcmp.movies.network.api_services.MoviesApiService
import com.zg.netflixcmp.movies.persistence.daos.MovieDao
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull

class MovieRepositoryTest {

    private lateinit var repo: MoviesRepository
    private lateinit var apiService: MoviesApiService
    private lateinit var movieDao: MovieDao

    @BeforeTest
    fun setUp() {
        apiService = MockMovieApiService()
        movieDao = MockMovieDao()

        repo = MoviesRepository(apiService, movieDao)
    }

    @Test
    fun getNowPlayingMovie_apiSucceeds_returnsMovieList() {
        runTest {
            val movieListResponse = repo.getNowPlayingMovies()
            assertNotNull(movieListResponse)
            assertEquals(movieListResponse.results.first(), mockNowPlayingMovies.first())
        }
    }

    @Test
    fun getFeaturedMovie_apiSucceeds_returnsFeaturedMovie() {
        runTest {
            val featuredMovie = repo.getFeaturedMovie()
            assertNotNull(featuredMovie)
            assertEquals(featuredMovie.id, mockMovieDetails.id)
        }
    }

    @Test
    fun getFeaturedMovieFromDb_dbSucceeds_returnsFeaturedMovie() {
        runTest {
            repo.getNowPlayingMovies()
            val featuredMovieFromDb = repo.getFeaturedMovieFromDb()
            assertNotNull(featuredMovieFromDb)
            assertEquals(featuredMovieFromDb, mockNowPlayingMovies.first())
        }
    }

    @Test
    fun getMoviesByGenre_apiSucceeds_returnsMoviesByGenre() {
        runTest {
            val firstGenreId = mockGenres.first().id
            val moviesByGenre = repo.getMoviesByGenre(genreId = firstGenreId)
            assertNotNull(moviesByGenre)
            assertNotEquals(moviesByGenre.count(), 0)
            assertEquals(moviesByGenre, mockMoviesByGenre[firstGenreId])
        }
    }

    // TODO: - Get Movies By First Five Genres

    // TODO: - Get Movie Details
}