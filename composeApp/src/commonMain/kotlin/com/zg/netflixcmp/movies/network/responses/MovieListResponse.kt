package com.zg.netflixcmp.movies.network.responses

import com.zg.netflixcmp.movies.data.vos.DateVO
import com.zg.netflixcmp.movies.data.vos.MovieVO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieListResponse(
    @SerialName("dates")
    val dates: DateVO?,
    @SerialName("page")
    val page: Int,
    @SerialName("results")
    val results: List<MovieVO>
)