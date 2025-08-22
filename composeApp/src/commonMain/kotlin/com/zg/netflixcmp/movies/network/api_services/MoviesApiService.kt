package com.zg.netflixcmp.movies.network.api_services

import com.zg.netflixcmp.movies.data.vos.MovieVO
import com.zg.netflixcmp.movies.network.responses.GenreListResponse
import com.zg.netflixcmp.movies.network.responses.MovieListResponse

interface MoviesApiService {
    suspend fun getNowPlayingMovies(page: Int): MovieListResponse?

    suspend fun getGenres() : GenreListResponse?

    suspend fun getMoviesByGenre(genreId : Int) : MovieListResponse?

    suspend fun getMovieDetails(movieId : Int) : MovieVO?
}