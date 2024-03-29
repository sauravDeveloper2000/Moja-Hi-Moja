package com.example.mojahimoja.navigationInfo

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.mojahimoja.ui.homeScreen.HomeScreen
import com.example.mojahimoja.ui.loginScreen.LoginScreen
import com.example.mojahimoja.ui.registrationScreen.RegistrationScreen

@Composable
fun AppNavigation(
    startingDestination: Destinations,
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = startingDestination.route) {

        // PreAuth
        navigation(
            startDestination = Destinations.PreAuth.Login.route,
            route = Destinations.PreAuth.route
        ){
            composable(
                route = Destinations.PreAuth.Login.route
            ){
                LoginScreen(
                    navigateToRegistrationScreen = {
                        navigateAndPopUp(
                            navController = navController,
                            route = Destinations.PreAuth.Register.route,
                            popUp = Destinations.PreAuth.Login.route
                        )
                    }
                )
            }

            composable(
                route = Destinations.PreAuth.Register.route
            ){
                RegistrationScreen(
                    backToLoginPage = {
                        navigateAndPopUp(
                            navController = navController,
                            route = Destinations.PreAuth.Login.route,
                            popUp = Destinations.PreAuth.Register.route
                        )
                    }
                )
            }
        }

        // Post Auth
        composable(
            route = Destinations.HomeScreen.route
        ){
            HomeScreen(
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}


/**
 * An helper method, which navigates to our desired destination by removing our current destination.
 */
fun navigateAndPopUp(
    navController: NavHostController,
    route: String,
    popUp: String
) {
    navController.navigate(route) {
        launchSingleTop = true
        popUpTo(popUp) { inclusive = true }
    }
}