package com.example.letssopt.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

class AppState(
    val navController: NavHostController
) {
    val startDestination: Route = Route.Login

    fun navigateToSignUp() {
        navController.navigate(Route.SignUp)
    }

    fun navigateToUserProfile(userId: Long) {
        navController.navigate(Route.UserProfile(userId))
    }
}

@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController()
) = remember(navController) {
    AppState(navController)
}

