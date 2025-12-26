package com.zg.netflixcmp.movies.network.api_services.impls

import com.zg.netflixcmp.core.network.HttpClientProvider
import com.zg.netflixcmp.core.network.transformResult
import com.zg.netflixcmp.core.utils.API_KEY
import com.zg.netflixcmp.core.utils.GET_GENRES
import com.zg.netflixcmp.core.utils.GET_MOVIES_BY_GENRE
import com.zg.netflixcmp.core.utils.GET_MOVIE_DETAILS
import com.zg.netflixcmp.core.utils.NOW_PLAYING_MOVIES
import com.zg.netflixcmp.movies.data.vos.MovieVO
import com.zg.netflixcmp.movies.network.api_services.MoviesApiService
import com.zg.netflixcmp.movies.network.responses.GenreListResponse
import com.zg.netflixcmp.movies.network.responses.MovieListResponse
import io.ktor.client.request.get
import io.ktor.client.request.header

class MoviesApiServiceImpl : MoviesApiService {
    override suspend fun getNowPlayingMovies(page: Int): MovieListResponse? {
        val httpResponse = HttpClientProvider.httpClient.get("$NOW_PLAYING_MOVIES?page=$page") {
            header("Authorization", "Bearer $API_KEY")
        }

        return transformResult<MovieListResponse?>(httpResponse)
    }

    override suspend fun getGenres(): GenreListResponse? {
        val httpResponse = HttpClientProvider.httpClient.get(GET_GENRES) {
            header("Authorization", "Bearer $API_KEY")
        }

        return transformResult(httpResponse)
    }

    override suspend fun getMoviesByGenre(genreId: Int): MovieListResponse? {
        val httpResponse =
            HttpClientProvider.httpClient.get("$GET_MOVIES_BY_GENRE?with_genres=$genreId") {
                header("Authorization", "Bearer $API_KEY")
            }

        return transformResult(httpResponse)
    }

    override suspend fun getMovieDetails(movieId: Int): MovieVO? {
        val httpResponse = HttpClientProvider.httpClient.get("$GET_MOVIE_DETAILS/$movieId") {
            header("Authorization", "Bearer $API_KEY")
        }

        return transformResult(httpResponse)
    }
}