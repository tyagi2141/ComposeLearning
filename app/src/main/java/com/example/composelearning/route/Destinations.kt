package com.example.composelearning.route

import androidx.navigation.NavType
import androidx.navigation.navArgument

interface Destinations {
    val title: String
    val route: String
    val routeWithArgs: String
}

object Login : Destinations {
    override val title: String
        get() = "Login"
    override val route: String
        get() = "dashboard"
    override val routeWithArgs: String
        get() = route

}

object DashBoard : Destinations {
    override val title: String
        get() = "Dashboard"
    override val route: String
        get() = "Dashboard"
    override val routeWithArgs: String
        get() = "$route/{name}/{pass}"

    val argument = listOf(
        navArgument(name = "name") { type = NavType.IntType },
        navArgument(name = "pass") { type = NavType.StringType }
    )

}


object DetailView : Destinations {
    override val title: String
        get() = "Detail"
    override val route: String
        get() = "detail"
    override val routeWithArgs: String
        get() = "$route/{movieId}"

    val argument = listOf(
        navArgument(name = "movieId") { type = NavType.IntType },
    )

}


val movieDestination = listOf(Login, DashBoard,DetailView)