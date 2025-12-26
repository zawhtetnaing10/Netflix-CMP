package com.zg.netflixcmp.movies.data.repositories

import com.zg.netflixcmp.movies.data.vos.GenreVO
import com.zg.netflixcmp.movies.data.vos.MovieVO
import com.zg.netflixcmp.movies.network.responses.MovieListResponse
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    suspend fun getNowPlayingMovies(): MovieListResponse?

    suspend fun getFeaturedMovie(): MovieVO?

    suspend fun getFeaturedMovieFromDb(): MovieVO?

    suspend fun getMoviesByIdFromDb(movieId: Int): Flow<MovieVO?>

    suspend fun getGenres(): List<GenreVO>

    suspend fun getMoviesWithFirstFiveGenres(): List<Pair<GenreVO, List<MovieVO>>>

    suspend fun getMoviesByGenre(genreId: Int): List<MovieVO>

    suspend fun getMovieDetails(movieId: Int): MovieVO?
}