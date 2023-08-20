package com.example.composelearning.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
internal data class MoviesResponse(
    @SerialName("page") var page: Int? = null,
    @SerialName("results") var results: ArrayList<MovieRemoteModel> = arrayListOf(),
    @SerialName("total_pages") var totalPages: Int? = null,
    @SerialName("total_results") var totalResults: Int? = null
)
