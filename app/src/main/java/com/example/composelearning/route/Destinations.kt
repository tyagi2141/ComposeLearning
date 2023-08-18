package com.example.composelearning.route

import androidx.navigation.NavArgs
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
        get() = "dashboard"
    override val routeWithArgs: String
        get() = "$route/{name}"

    val argument = listOf(
        navArgument(name = "name") { type = NavType.IntType }
    )

}

val movieDestination = listOf(Login, DashBoard)