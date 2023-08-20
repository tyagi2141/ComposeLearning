package com.example.composelearning.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composelearning.model.Movie
import com.example.composelearning.usecase.GetMoviesUseCase
import kotlinx.coroutines.launch

class DashBoardViewModel(val getMoviesUseCase: GetMoviesUseCase) : ViewModel() {


    var uiState by mutableStateOf(DashBoardState())
    private var currentPage = 1

    init {
        loadMovies(false)
    }


    fun loadMovies(forceReload: Boolean = false){
        if (uiState.loading) return
        if (forceReload) currentPage = 1
        if (currentPage == 1) uiState = uiState.copy(refreshState = true)

        viewModelScope.launch {
            uiState = uiState.copy(
                loading = true
            )

            try {
                val resultMovies = getMoviesUseCase(page = currentPage)
                val movies = if (currentPage == 1) resultMovies else uiState.movieList + resultMovies

                currentPage += 1
                uiState = uiState.copy(
                    loading = false,
                    refreshState = false,
                    loadFinished = resultMovies.isEmpty(),
                    movieList = movies
                )

            }catch (error: Throwable){
                uiState = uiState.copy(
                    loading = false,
                    refreshState = false,
                    loadFinished = true,
                    errorMessage = "Could not load movies: ${error.localizedMessage}"
                )
            }
        }
    }
}

data class DashBoardState(
    val loading: Boolean = false,
    val movieList: List<Movie> = emptyList(),
    val refreshState: Boolean = false,
    val errorMessage: String? = "",
    val loadFinished: Boolean = false
)
