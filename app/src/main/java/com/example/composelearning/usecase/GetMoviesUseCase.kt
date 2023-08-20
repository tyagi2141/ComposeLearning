package com.example.composelearning.usecase

import com.example.composelearning.model.Movie
import com.example.composelearning.remote.MovieRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetMoviesUseCase:KoinComponent {

    private val movieRepository : MovieRepository by inject()

   /* suspend operator fun invoke(page:Int): List<Movie> {
        return movieRepository.getMovies(page)
    }*/

    @Throws(Exception::class)
    suspend operator fun invoke(page: Int): List<Movie>{
        return movieRepository.getMovies(page = page)
    }
}