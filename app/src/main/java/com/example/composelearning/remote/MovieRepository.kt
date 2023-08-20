package com.example.composelearning.remote

import com.example.composelearning.model.Movie


interface MovieRepository {

   suspend fun getMovies(page:Int):List<Movie>

   suspend fun getMovie(movieId:Int):Movie
}