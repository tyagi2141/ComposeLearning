package com.example.composelearning.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composelearning.model.Movie
import com.example.composelearning.usecase.GetMovieUseCase
import kotlinx.coroutines.launch

class DetailViewModel(val movieUseCase: GetMovieUseCase, movieId: Int) : ViewModel() {

    var uiState by mutableStateOf(DetailScreenState())

    init {
        loadMovie(movieId)
    }

    fun loadMovie(movieId: Int) {
        viewModelScope.launch {
            uiState = uiState.copy(loading = true)
            uiState = try {
                val movie = movieUseCase.invoke(movieId = movieId)
                uiState.copy(movie = movie, loading = false)
            } catch (e: Exception) {
                uiState.copy(loading = false, error = e.toString())
            }

        }
    }


}

data class DetailScreenState(
    val loading: Boolean? = false,
    val movie: Movie? = null,
    val error: String? = ""
)