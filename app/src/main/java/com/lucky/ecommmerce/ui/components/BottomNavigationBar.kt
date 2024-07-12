package com.lucky.ecommmerce.ui.components

import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController

@Composable
fun BottomNavigationBar(navController: NavController) {
    NavigationBar {
        val items = listOf(
            Screen.Home,
            Screen.Shop,
            Screen.Favorites,
            Screen.Profile
        )
        items.forEach { screen ->
            NavigationBarItem(
                icon = { Icon(imageVector = screen.icon, contentDescription = null) },
                label = { Text(screen.title) },
                selected = false, // Update based on your selection logic
                onClick = {
                    navController.navigate(screen.route)
                }
            )
        }
    }
}

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    data object Home : Screen("home", "Home", Icons.Filled.Home)
    data object Shop : Screen("category", "categories", Icons.Filled.ShoppingCart)
    data object Favorites : Screen("favorites", "Favorites", Icons.Filled.Favorite)
    data object Profile : Screen("profile", "Profile", Icons.Filled.Person)
}
