package com.zg.netflixcmp.movies.data.vos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DateVO(
    @SerialName("maximum")
    val maximum: String,
    @SerialName("minimum")
    val minimum: String
)