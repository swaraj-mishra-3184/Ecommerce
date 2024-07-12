package com.lucky.ecommmerce.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lucky.ecommmerce.data.api.ApiServiceInstance
import com.lucky.ecommmerce.data.model.Order
import com.lucky.ecommmerce.data.repository.OrderRepository
import kotlinx.coroutines.launch

@Composable
fun OrderScreen(navController: NavController) {
    val apiService = ApiServiceInstance.apiService
    val orderRepository = OrderRepository(apiService)
    var address by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = address,
            onValueChange = { address = it },
            label = { Text("Delivery Address") },
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        )
        Button(onClick = {
            coroutineScope.launch {
                val response = orderRepository.placeOrder(Order(0, 1, 1, 1, address, "Pending"))
                if (response.isSuccessful) {
                    navController.navigate("orderConfirmation")
                } else {
                    // Handle error
                }
            }
        }) {
            Text("Place Order")
        }
    }
}
