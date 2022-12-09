package com.example.myrestaurantcompose.ui.navigation

sealed class Screen (val route: String){
    object Home: Screen("home")
    object Profile: Screen("profile")
}