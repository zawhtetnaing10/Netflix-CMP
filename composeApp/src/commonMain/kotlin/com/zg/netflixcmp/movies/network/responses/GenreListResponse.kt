package com.zg.netflixcmp.movies.network.responses

import com.zg.netflixcmp.movies.data.vos.GenreVO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenreListResponse(
    @SerialName("genres")
    val genres : List<GenreVO>
)