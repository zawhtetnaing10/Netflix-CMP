package com.zg.netflixcmp.mock

import com.zg.netflixcmp.mock.data.mockGenres
import com.zg.netflixcmp.mock.data.mockMovieDetails
import com.zg.netflixcmp.mock.data.mockMoviesByGenre
import com.zg.netflixcmp.mock.data.mockNowPlayingMovies
import com.zg.netflixcmp.movies.data.vos.DateVO
import com.zg.netflixcmp.movies.data.vos.MovieVO
import com.zg.netflixcmp.movies.network.api_services.MoviesApiService
import com.zg.netflixcmp.movies.network.responses.GenreListResponse
import com.zg.netflixcmp.movies.network.responses.MovieListResponse

class MockMovieApiService : MoviesApiService {
    override suspend fun getNowPlayingMovies(page: Int): MovieListResponse? {
        return MovieListResponse(
            dates = DateVO(
                maximum = "2025-12-31",
                minimum = "2025-11-19"
            ),
            page = 1,
            results = mockNowPlayingMovies
        )
    }

    override suspend fun getGenres(): GenreListResponse? {
        return GenreListResponse(
            genres = mockGenres,
        )
    }

    override suspend fun getMoviesByGenre(genreId: Int): MovieListResponse? {
        return MovieListResponse(
            dates = null,
            page = 1,
            results = mockMoviesByGenre[genreId] ?: listOf()
        )
    }

    override suspend fun getMovieDetails(movieId: Int): MovieVO? {
        return mockMovieDetails
    }
}