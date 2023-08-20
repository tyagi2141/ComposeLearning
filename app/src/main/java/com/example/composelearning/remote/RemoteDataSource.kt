package com.example.composelearning.remote

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class RemoteDataSource(val apiService: MovieService) {

    suspend fun getMovies(page: Int) = withContext(Dispatchers.IO) {
        apiService.getMovies(page = page)
    }

    suspend fun getMovie(movieId: Int) = withContext(Dispatchers.IO) {
        apiService.getMovie(movieId)
    }
}