package com.zg.netflixcmp

import com.zg.netflixcmp.mock.MockMovieApiService
import com.zg.netflixcmp.mock.MockMovieDao
import com.zg.netflixcmp.mock.data.mockGenres
import com.zg.netflixcmp.mock.data.mockMovieDetails
import com.zg.netflixcmp.mock.data.mockMoviesByGenre
import com.zg.netflixcmp.mock.data.mockNowPlayingMovies
import com.zg.netflixcmp.movies.data.repositories.MoviesRepositoryImpl
import com.zg.netflixcmp.movies.data.vos.GenreVO
import com.zg.netflixcmp.movies.data.vos.MovieVO
import com.zg.netflixcmp.movies.network.api_services.MoviesApiService
import com.zg.netflixcmp.movies.persistence.daos.MovieDao
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull

class MovieRepositoryTest {

    private lateinit var repo: MoviesRepositoryImpl
    private lateinit var apiService: MoviesApiService
    private lateinit var movieDao: MovieDao

    @BeforeTest
    fun setUp() {
        apiService = MockMovieApiService()
        movieDao = MockMovieDao()

        repo = MoviesRepositoryImpl(apiService, movieDao)
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

    @Test
    fun getMoviesByFirstFiveGenres_apiSucceeds_returnsMoviesByGenre() {
        runTest {
            val result: List<Pair<GenreVO, List<MovieVO>>> = repo.getMoviesWithFirstFiveGenres()

            val dataToCompare: MutableList<Pair<GenreVO, List<MovieVO>>> = mutableListOf()
            mockGenres.take(5).forEach {
                val moviesByGenre = mockMoviesByGenre[it.id] ?: listOf()
                dataToCompare.add(Pair(it, moviesByGenre))
            }

            assertEquals(result, dataToCompare)
        }
    }

    @Test
    fun getMovieDetails_apiSucceeds_returnsMovieDetails() {
        runTest {
            val movieDetails = repo.getMovieDetails(28)
            assertEquals(movieDetails, mockMovieDetails)
        }
    }

    @Test
    fun getMovieByIdFromDb_dbSucceeds_returnsMovieDetails(){
        runTest {
            val nowPlayingMovies = repo.getNowPlayingMovies()?.results ?: listOf()

            val movieToTest = repo.getMoviesByIdFromDb(nowPlayingMovies.first().id).first()
            assertEquals(movieToTest, nowPlayingMovies.first())
        }
    }

    @Test
    fun getGenres_apiSucceeds_returnsGenreList(){
        runTest {
            val genres = repo.getGenres()
            assertEquals(genres , mockGenres)
        }
    }
}