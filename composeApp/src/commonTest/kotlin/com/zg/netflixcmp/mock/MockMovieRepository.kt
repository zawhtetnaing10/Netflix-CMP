package com.zg.netflixcmp.mock

import com.zg.netflixcmp.mock.data.mockGenres
import com.zg.netflixcmp.mock.data.mockMovieDetails
import com.zg.netflixcmp.mock.data.mockMoviesByGenre
import com.zg.netflixcmp.mock.data.mockNowPlayingMovies
import com.zg.netflixcmp.movies.data.repositories.MoviesRepository
import com.zg.netflixcmp.movies.data.vos.DateVO
import com.zg.netflixcmp.movies.data.vos.GenreVO
import com.zg.netflixcmp.movies.data.vos.MovieVO
import com.zg.netflixcmp.movies.network.responses.MovieListResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class MockMovieRepository : MoviesRepository {
    override suspend fun getNowPlayingMovies(): MovieListResponse? {
        return MovieListResponse(
            dates = DateVO(
                maximum = "2025-12-31",
                minimum = "2025-11-19"
            ),
            page = 1,
            results = mockNowPlayingMovies
        )
    }

    override suspend fun getFeaturedMovie(): MovieVO? {
        return mockNowPlayingMovies.first()
    }

    override suspend fun getFeaturedMovieFromDb(): MovieVO? {
        return mockNowPlayingMovies.first()
    }

    override suspend fun getMoviesByIdFromDb(movieId: Int): Flow<MovieVO?> {
        return flowOf(mockMovieDetails)
    }

    override suspend fun getGenres(): List<GenreVO> {
        return mockGenres
    }

    override suspend fun getMoviesWithFirstFiveGenres(): List<Pair<GenreVO, List<MovieVO>>> {
        val result: MutableList<Pair<GenreVO, List<MovieVO>>> = mutableListOf()
        mockGenres.take(5).forEach {
            val moviesByGenre = mockMoviesByGenre[it.id] ?: listOf()
            result.add(Pair(it, moviesByGenre))
        }
        return result
    }

    override suspend fun getMoviesByGenre(genreId: Int): List<MovieVO> {
        return mockMoviesByGenre[genreId] ?: listOf()
    }

    override suspend fun getMovieDetails(movieId: Int): MovieVO? {
        return mockMovieDetails
    }
}