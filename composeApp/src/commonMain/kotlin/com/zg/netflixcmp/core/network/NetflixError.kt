package com.zg.netflixcmp.core.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetflixError(
    @SerialName("status_code")
    val statusCode : Int,
    @SerialName("status_message")
    val statusMessage : String,
    @SerialName("success")
    val success : Boolean
)