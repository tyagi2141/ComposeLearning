package com.example.composelearning.remote

import com.example.composelearning.model.Movie
import com.example.composelearning.util.toMovies

internal class MovieRepositoryImpl(private val remoteDataSource: RemoteDataSource) :
    MovieRepository {
    override suspend fun getMovies(page: Int): List<Movie> {
        return remoteDataSource.getMovies(page = page).results.map {
            it.toMovies()
        }
    }

    override suspend fun getMovie(movieId: Int): Movie {
        return remoteDataSource.getMovie(movieId).toMovies()
    }
}