package com.zg.netflixcmp.core.network

import com.zg.netflixcmp.core.utils.BASE_URL
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import kotlin.time.Duration.Companion.seconds

object HttpClientProvider {
    val httpClient by lazy {
        HttpClient {
            // Json serialization
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    explicitNulls = false
                    prettyPrint = true
                    // Use alternative names could be ignored here.
                })
            }

            // Default Request
            install(DefaultRequest) {
                url(BASE_URL)

                header("Accept", "application/json")
                header("Content-Type", "application/json")
            }

            // Time outs
            install(HttpTimeout){
                connectTimeoutMillis = 30.seconds.inWholeMilliseconds
                socketTimeoutMillis = 10.seconds.inWholeMilliseconds
                requestTimeoutMillis = 30.seconds.inWholeMilliseconds
            }
        }
    }
}