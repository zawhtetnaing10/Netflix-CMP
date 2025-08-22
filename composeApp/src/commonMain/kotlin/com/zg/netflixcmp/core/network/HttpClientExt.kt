package com.zg.netflixcmp.core.network

import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.serialization.JsonConvertException

// Transform result
suspend inline fun <reified T> transformResult(httpResponse: HttpResponse): T {
    when (httpResponse.status.value) {
        in 200..299 -> {
            return httpResponse.body<T>()
        }

        else -> {
            try {
                val errorResponse = httpResponse.body<NetflixError>()
                throw Exception(errorResponse.statusMessage)
            } catch (_: JsonConvertException) {
                throw Exception(httpResponse.bodyAsText())
            }
        }
    }
}