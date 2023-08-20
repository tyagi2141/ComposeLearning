package com.example.composelearning.remote

import com.example.composelearning.model.MovieRemoteModel
import com.example.composelearning.model.MoviesResponse
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

internal class MovieService : KtorApi() {

    suspend fun getMovies(page: Int = 1): MoviesResponse = client.get {
        pathUrl("movie/popular")
        parameter("page", page)
    }.body()

    suspend fun getMovie(movieId: Int): MovieRemoteModel = client.get {
        pathUrl("movie/${movieId}")
    }.body()
}