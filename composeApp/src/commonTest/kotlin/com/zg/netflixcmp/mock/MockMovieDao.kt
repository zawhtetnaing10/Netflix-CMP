package com.zg.netflixcmp.mock

import com.zg.netflixcmp.movies.data.vos.MovieVO
import com.zg.netflixcmp.movies.persistence.daos.MovieDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class MockMovieDao : MovieDao {

    val moviesInDB: MutableList<MovieVO> = mutableListOf()

    override suspend fun insertMovies(movies: List<MovieVO>) {
        moviesInDB.addAll(movies)
    }

    override fun getMovieById(movieId: Int): Flow<MovieVO?> {
        return flowOf(moviesInDB.firstOrNull { it.id == movieId })
    }

    override suspend fun getFeaturedMovie(): MovieVO? {
        return moviesInDB.firstOrNull()
    }

    override suspend fun getAllMovies(): List<MovieVO> {
        return moviesInDB
    }

    override suspend fun saveSingleMovie(movie: MovieVO) {
        moviesInDB.add(movie)
    }

    override suspend fun deleteAllMovies() {
        moviesInDB.clear()
    }
}