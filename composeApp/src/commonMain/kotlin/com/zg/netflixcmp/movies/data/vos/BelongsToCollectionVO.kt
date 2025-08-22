package com.zg.netflixcmp.movies.data.vos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BelongsToCollectionVO(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("poster_path")
    val posterPath: String,
    @SerialName("backdrop_path")
    val backdropPath: String
)