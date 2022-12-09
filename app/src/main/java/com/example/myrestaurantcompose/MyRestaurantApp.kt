package com.example.myrestaurantcompose

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myrestaurantcompose.ui.navigation.NavigationItem
import com.example.myrestaurantcompose.ui.navigation.Screen
import com.example.myrestaurantcompose.ui.screen.home.HomeScreen
import com.example.myrestaurantcompose.ui.screen.profile.ProfileScreen
import com.example.myrestaurantcompose.ui.theme.MyRestaurantComposeTheme


@Composable
fun MyRestaurantApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        bottomBar = {
            BottomBar(navController)
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ){
            composable(Screen.Home.route){
                HomeScreen()
            }
            composable(Screen.Profile.route){
                ProfileScreen()
            }
        }


    }
}

@Composable
fun BottomBar(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.primary,
        modifier = modifier
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        val navigationItem = listOf(
            NavigationItem(
                title = stringResource(R.string.menu_home),
                icon = Icons.Default.Home,
                screen = Screen.Home
            ),
            NavigationItem(
                title = stringResource(R.string.menu_profile),
                icon = Icons.Default.AccountCircle,
                screen = Screen.Profile
            )
        )

        navigationItem.map {items->
            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = items.icon,
                        contentDescription = items.title
                    )
                },
                label = {
                    Text(items.title)
                },
                selected = currentRoute == items.screen.route,
                unselectedContentColor = Color.LightGray,
                onClick = {
                    navController.navigate(items.screen.route){
                        popUpTo(navController.graph.findStartDestination().id){
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, device = Devices.PIXEL_4_XL)
@Composable
fun MyRestaurantAppPreview() {
    MyRestaurantComposeTheme {
        MyRestaurantApp()
    }
}

