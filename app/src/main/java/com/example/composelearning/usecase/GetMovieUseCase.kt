package com.example.composelearning.usecase

import com.example.composelearning.model.Movie
import com.example.composelearning.remote.MovieRepository

class GetMovieUseCase(val movieRepository: MovieRepository) {

    suspend operator fun invoke(movieId:Int): Movie {
       return movieRepository.getMovie(movieId = movieId)
    }
}