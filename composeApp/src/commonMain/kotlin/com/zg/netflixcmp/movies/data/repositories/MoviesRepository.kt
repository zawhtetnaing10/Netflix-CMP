package com.zg.netflixcmp.movies.data.repositories

import com.zg.netflixcmp.core.persistence.DatabaseProvider
import com.zg.netflixcmp.movies.data.vos.GenreVO
import com.zg.netflixcmp.movies.data.vos.MovieVO
import com.zg.netflixcmp.movies.network.api_services.MoviesApiService
import com.zg.netflixcmp.movies.network.api_services.impls.MoviesApiServiceImpl
import com.zg.netflixcmp.movies.network.responses.MovieListResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object MoviesRepository {
    val movieApiService: MoviesApiService = MoviesApiServiceImpl

    // Database
    internal val database = DatabaseProvider.database

    suspend fun getNowPlayingMovies(): MovieListResponse? {
        return withContext(Dispatchers.IO) {
            val nowPlayingMovies = movieApiService.getNowPlayingMovies(1)

            launch {
                nowPlayingMovies?.results?.let {
                    database?.insertMovies(it)
                }
            }

            return@withContext nowPlayingMovies
        }
    }

    suspend fun getFeaturedMovie(): MovieVO? {
        return withContext(Dispatchers.IO) {
            val firstNowPlayingMovie = getNowPlayingMovies()?.results?.first()

            firstNowPlayingMovie?.let {
                return@withContext getMovieDetails(firstNowPlayingMovie.id)
            }
        }
    }

    // Get featured movie from db
    suspend fun getFeaturedMovieFromDB(): MovieVO? {
        return withContext(Dispatchers.IO) {
            return@withContext database?.getFirstMovie()
        }
    }

    // Get movie by id from db
    suspend fun getMovieByIdFromDb(movieId: Int): MovieVO? {
        return withContext(Dispatchers.IO) {
            return@withContext database?.getMovieById(movieId)
        }
    }

    suspend fun getGenres(): List<GenreVO> {
        return withContext(Dispatchers.IO) {
            val genreListResponse = movieApiService.getGenres()
            genreListResponse?.genres ?: listOf()
        }
    }

    suspend fun getMoviesWithFirstFiveGenres(): List<Pair<GenreVO, List<MovieVO>>> {
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

    suspend fun getMoviesByGenre(genreId: Int): List<MovieVO> {
        return withContext(Dispatchers.IO) {
            val response = movieApiService.getMoviesByGenre(genreId)
            response?.results ?: listOf()
        }
    }

    suspend fun getMovieDetails(movieId: Int): MovieVO? {
        return withContext(Dispatchers.IO) {
            val movieDetails = movieApiService.getMovieDetails(movieId)

            launch {
                movieDetails?.let {
                    database?.insertSingleMovieFull(movieDetails)
                }
            }

            return@withContext movieDetails
        }
    }
}