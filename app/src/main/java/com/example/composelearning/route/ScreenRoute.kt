package com.example.composelearning.route

import android.util.Log
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.composelearning.design.DashBoard
import com.example.composelearning.design.DetailViewScreen
import com.example.composelearning.design.LoginScree
import com.example.composelearning.viewModel.DashBoardViewModel
import com.example.composelearning.viewModel.DetailViewModel
import com.example.composelearning.viewModel.LoginViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import io.ktor.http.parametersOf
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf


@Composable
fun ScreenRoute() {

    val navController = rememberNavController()
    val systemUiController = rememberSystemUiController()
    val isSystemDark = isSystemInDarkTheme()

    val scaffoldState = rememberScaffoldState()
    val statusBarColor = if (isSystemDark) {
        MaterialTheme.colors.primaryVariant
    } else {
        Color.Transparent
    }

    SideEffect {
        systemUiController.setStatusBarColor(statusBarColor, darkIcons = !isSystemDark)
    }

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = movieDestination.find {
        backStackEntry?.destination?.route == it.route || backStackEntry?.destination?.route == it.routeWithArgs
    } ?: Login

    Scaffold(scaffoldState = scaffoldState, topBar = {
        MovieAppBar(
            canNavigateBack = navController.previousBackStackEntry != null,
            currentScreen = currentScreen
        ) {
            navController.navigateUp()
        }
    }) { innerpadding ->
        NavHost(
            navController = navController,
            modifier = Modifier.padding(innerpadding),
            startDestination = Login.routeWithArgs
        ) {
            composable(Login.routeWithArgs) {
                val loginViewModel: LoginViewModel = viewModel()


                LoginScree(
                    modifier = Modifier,
                    uiState = loginViewModel.uiStateLogin,
                    onClick = {
                        navController.popBackStack(DashBoard.route, true)
                        navController.navigate("${DashBoard.route}/${loginViewModel.uiStateLogin.name}/${loginViewModel.uiStateLogin.password}")

                    },
                )
            }


            composable(DashBoard.routeWithArgs) {
                val dashboardViewModel: DashBoardViewModel = koinViewModel()

                Log.e("vjhvhjvhjv", "${dashboardViewModel.uiState.movieList}")
                Log.e("vjhvhjvhjv errror", "${dashboardViewModel.uiState.errorMessage}")
                val name = it.arguments?.getString("name") ?: "0"
                val pass = it.arguments?.getString("pass") ?: "0"
                DashBoard(
                    name = name,
                    password = pass,
                    uiState = dashboardViewModel.uiState,
                    loadNextMovie = { dashboardViewModel.loadMovies(forceReload = it) },
                    navigateToDetail = {
                        navController.navigate("${DetailView.route}/${it.id}")
                    }
                )
            }

            composable(DetailView.routeWithArgs,arguments = DetailView.argument) {
                val movieId = it.arguments?.getInt("movieId") ?: 0
                val detailVM: DetailViewModel = koinViewModel(parameters = { parametersOf(movieId) })
                DetailViewScreen(detailVM.uiState)
            }
        }

    }
}

