package com.farhansolih0009.assesment1.navigation

sealed class Screen(val route: String) {
    object Home : Screen("mainScreen")
    object WeightConverter : Screen("weightConverter")
    object LengthConverter : Screen("lengthConverter")
    object AboutScreen : Screen("about")

}
