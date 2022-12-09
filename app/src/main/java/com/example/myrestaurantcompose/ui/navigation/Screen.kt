package com.example.myrestaurantcompose.ui.navigation

sealed class Screen (val route: String){
    object Home: Screen("home")
    object Search: Screen("search")
    object Profile: Screen("profile")
    object DetailRestaurant : Screen("home/{restaurantId}") {
        fun createRoute(restaurantId: String) = "home/$restaurantId"
    }
}