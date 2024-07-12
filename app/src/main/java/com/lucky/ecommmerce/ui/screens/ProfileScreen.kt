package com.lucky.ecommmerce.ui.screens

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.room.util.query
import coil.compose.rememberImagePainter
import com.lucky.ecommmerce.R
import com.lucky.ecommmerce.data.SharedPreferencesManager
import com.lucky.ecommmerce.data.api.ApiServiceInstance
import com.lucky.ecommmerce.data.model.Order
import com.lucky.ecommmerce.data.model.User
import com.lucky.ecommmerce.data.repository.UserRepository
import com.lucky.ecommmerce.ui.components.BottomNavigationBar
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileScreen(navController: NavController, context: Context) {
    val apiService = ApiServiceInstance.apiService
    val userRepository = UserRepository(apiService)
    var user by remember { mutableStateOf<User?>(null) }
    var orders by remember { mutableStateOf(emptyList<Order>()) }
    val coroutineScope = rememberCoroutineScope()
    val sharedPreferencesManager = remember { SharedPreferencesManager(context) }

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            val response = userRepository.getUserOrders(1)
            if (response.isSuccessful) {
                orders = response.body() ?: emptyList()
            } else {
                // Handle error
            }
        }
    }
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "My profile",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            user?.let {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = null,
                        modifier = Modifier
                            .size(64.dp)
                            .clip(CircleShape)
                    )

                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(it.name, style = MaterialTheme.typography.titleMedium)
                        Text(
                            it.email,
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit",
                        modifier = Modifier
                            .size(24.dp)
                            .clickable { navController.navigate("editProfile") }
                    )
                }
            }

            ProfileSection(
                navController,
                "My orders",
                "Already have ${orders.size} orders",
                "orders"
            )
            ProfileSection(navController, "Shipping addresses", "3 addresses", "addresses")
            ProfileSection(navController, "Payment methods", "Visa **34", "paymentMethods")
            ProfileSection(navController, "Promocodes", "You have special promocodes", "promocodes")
            ProfileSection(navController, "My reviews", "Reviews for 4 items", "reviews")
            ProfileSection(navController, "Settings", "Notifications, password", "settings")

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                sharedPreferencesManager.clear()
                navController.navigate("login")
            }) {
                Text("Logout")
            }

        }
    }
}

@Composable
fun ProfileSection(navController: NavController, title: String, subtitle: String, route: String) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .clickable { navController.navigate(route) }) {
        Text(title, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(vertical = 8.dp))
        Text(subtitle, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
        Divider(color = Color.Gray.copy(alpha = 0.5f), thickness = 0.5.dp, modifier = Modifier.padding(vertical = 8.dp))
    }
}
