package com.example.mojahimoja.navigationInfo

/**
 * This sealed class "Destinations" Holds the routes given to each destination.
 */
sealed class Destinations(
    val route: String
) {
    data object PreAuth: Destinations(route = "PreAuth"){
        data object Login: Destinations(route = "Login")
        data object Register: Destinations(route = "Register")
    }
    data object HomeScreen: Destinations(route = "Home")
}