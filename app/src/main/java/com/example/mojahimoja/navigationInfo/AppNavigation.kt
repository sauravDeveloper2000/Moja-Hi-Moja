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
                LoginScreen()
            }

            composable(
                route = Destinations.PreAuth.Register.route
            ){
                RegistrationScreen()
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