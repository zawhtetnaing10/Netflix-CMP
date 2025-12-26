package com.zg.netflixcmp.movies.data.repositories

import com.zg.netflixcmp.movies.data.vos.GenreVO
import com.zg.netflixcmp.movies.data.vos.MovieVO
import com.zg.netflixcmp.movies.network.api_services.MoviesApiService
import com.zg.netflixcmp.movies.network.responses.MovieListResponse
import com.zg.netflixcmp.movies.persistence.daos.MovieDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoviesRepositoryImpl(
    private val movieApiService: MoviesApiService,
    private val movieDao: MovieDao
) : MoviesRepository {

    override suspend fun getNowPlayingMovies(): MovieListResponse? {
        return withContext(Dispatchers.IO) {
            val nowPlayingMovies = movieApiService.getNowPlayingMovies(1)
            launch {
                movieDao.insertMovies(nowPlayingMovies?.results ?: listOf())
            }
            return@withContext nowPlayingMovies
        }
    }

    override suspend fun getFeaturedMovie(): MovieVO? {
        return withContext(Dispatchers.IO) {
            val firstNowPlayingMovie = getNowPlayingMovies()?.results?.first()

            firstNowPlayingMovie?.let {
                return@withContext getMovieDetails(firstNowPlayingMovie.id)
            }
        }
    }

    override suspend fun getFeaturedMovieFromDb(): MovieVO? {
        return movieDao.getFeaturedMovie()
    }

    override suspend fun getMoviesByIdFromDb(movieId: Int): Flow<MovieVO?> {
        return movieDao.getMovieById(movieId)
    }

    override suspend fun getGenres(): List<GenreVO> {
        return withContext(Dispatchers.IO) {
            val genreListResponse = movieApiService.getGenres()
            genreListResponse?.genres ?: listOf()
        }
    }

    override suspend fun getMoviesWithFirstFiveGenres(): List<Pair<GenreVO, List<MovieVO>>> {
        return withContext(Dispatchers.IO) {
            val genres = getGenres()

            val moviesByGenresDeferredList = genres.take(5).map { genre ->
                async {
                    val moviesByGenre = movieApiService.getMoviesByGenre(genre.id)
                    return@async Pair(genre, moviesByGenre?.results ?: listOf())
                }
            }

            moviesByGenresDeferredList.awaitAll()
        }
    }

    override suspend fun getMoviesByGenre(genreId: Int): List<MovieVO> {
        return withContext(Dispatchers.IO) {
            val response = movieApiService.getMoviesByGenre(genreId)
            response?.results ?: listOf()
        }
    }

    override suspend fun getMovieDetails(movieId: Int): MovieVO? {
        return withContext(Dispatchers.IO) {
            val movieDetails = movieApiService.getMovieDetails(movieId)
            movieDetails?.let {
                movieDao.saveSingleMovie(movieDetails)
            }
            movieDetails
        }
    }
}