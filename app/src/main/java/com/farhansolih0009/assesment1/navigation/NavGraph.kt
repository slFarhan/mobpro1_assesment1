package com.farhansolih0009.assesment1.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.farhansolih0009.assesment1.ui.screen.*

@Composable
fun AppNavigation(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            MainScreen(navController)
        }
        composable(Screen.WeightConverter.route) {
            WeightConverterScreen(navController)
        }
        composable(Screen.LengthConverter.route) {
            LengthConverterScreen(navController)
        }
        composable(Screen.AboutScreen.route) {
            AboutScreen(navController)
        }

    }
}
