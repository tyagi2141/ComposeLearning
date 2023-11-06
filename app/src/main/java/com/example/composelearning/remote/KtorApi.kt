package com.example.composelearning.remote

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.parameter
import io.ktor.http.path
import io.ktor.http.takeFrom
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


private const val BASE_URL = "https://api.themoviedb.org"
//private const val API_KEY = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJhNGQ5OTliMzhhODM5NjE5ZjFjM2VjYTk5ZTI4ZTZmMiIsInN1YiI6IjU4ZWQwNzMwOTI1MTQxNGIyODA1OGU4MiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.-_k0B_CrhF4yClJoMobuDkt8om2wbH6ZpZc3flM4TeY"
private const val API_KEY = "8c1a99064f1795f3ba344497293aa2c4"


internal abstract class KtorApi {
    val client = HttpClient {
        install(ContentNegotiation){
            json(Json {
                ignoreUnknownKeys = true
                useAlternativeNames = false
            })
        }
    }


    fun HttpRequestBuilder.pathUrl(path: String){
        url {
            takeFrom(BASE_URL)
            path("3", path)
           // parameter("Authorization", API_KEY)
           parameter("api_key", API_KEY)
        }
    }
}