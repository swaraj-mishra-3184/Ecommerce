package com.lucky.ecommmerce.ui.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lucky.ecommmerce.ui.screens.*

@Composable
fun NavGraph(navController: NavHostController, context: Context, startDestination: String) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable("login") { LoginScreen(navController, context) }
        composable("register") { RegistrationScreen(navController) }
        composable("home") { HomeScreen(navController) }
        composable("category") { CategoryScreen(navController) }
        composable("productList/{category}") { backStackEntry ->
            ProductListScreen(navController, backStackEntry.arguments?.getString("category") ?: "")
        }
        composable("profile") { ProfileScreen(navController, context) }
        composable("editProfile") { EditProfileScreen(navController) }
        composable("order") { OrderScreen(navController) }
        composable("orderConfirmation") { OrderConfirmationScreen(navController) }
    }
}
