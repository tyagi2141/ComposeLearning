package com.example.composelearning.util

import com.example.composelearning.model.Movie
import com.example.composelearning.model.MovieRemoteModel


internal fun MovieRemoteModel.toMovies(): Movie {
    return Movie(
        id = id?:0,
        title = title?:"",
        description = overview?:"",
        imageUrl = getImageUrl(posterPath),
        releaseDate = releaseDate?:""
    )
}

private fun getImageUrl(posterImage: String?) = "https://image.tmdb.org/t/p/w500/$posterImage"