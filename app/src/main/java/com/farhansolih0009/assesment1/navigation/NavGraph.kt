package com.farhansolih0009.assesment1.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.farhansolih0009.assesment1.ui.screen.MainScreen
//import com.farhansolih0009.assesment1.ui.screen.WeightConverterScreen
//import com.farhansolih0009.assesment1.ui.screen.LengthConverterScreen

@Composable
fun AppNavigation(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route // ✅ Menggunakan "mainScreen" sebagai start destination
    ) {
        composable(Screen.Home.route) {
            MainScreen(navController) // ✅ Rute ke MainScreen
        }
//        composable(Screen.WeightConverter.route) {
//            WeightConverterScreen(navController) // ✅ Rute ke WeightConverterScreen
//        }
//        composable(Screen.LengthConverter.route) {
//            LengthConverterScreen(navController) // ✅ Rute ke LengthConverterScreen
//        }
    }
}
