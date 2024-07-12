package com.lucky.ecommmerce.ui.screens

import androidx.navigation.NavHostController
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun OrderConfirmationScreen(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Thank you for your order!", modifier = Modifier.padding(16.dp))
        Text("Your order has been placed successfully.", modifier = Modifier.padding(16.dp))
    }
}
