package com.example.composelearning.route

import android.util.Log
import android.widget.Button
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
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.composelearning.design.DashBoard
import com.example.composelearning.design.LoginScree
import com.google.accompanist.systemuicontroller.rememberSystemUiController


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
                // val homeViewModel: HomeViewModel = koinViewModel()
                val context = LocalContext.current

                LoginScree(modifier = Modifier, context, onClick = {
                    navController.navigate( "${DashBoard.route}/${"rahul"}}")
                })


            }


            composable(DashBoard.routeWithArgs) {
                val name = it.arguments?.getString("name") ?: "0"
               DashBoard(name = name, password ="tyagi" )
                Log.e("dataaaaa....", "$name")
            }
        }

    }
}