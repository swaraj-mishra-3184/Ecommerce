package com.lucky.ecommmerce.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.lucky.ecommmerce.R
import com.lucky.ecommmerce.data.api.ApiServiceInstance
import com.lucky.ecommmerce.data.model.User
import com.lucky.ecommmerce.data.repository.UserRepository
import kotlinx.coroutines.launch

@Composable
fun RegistrationScreen(navController: NavController) {
    val apiService = ApiServiceInstance.apiService
    val userRepository = UserRepository(apiService)
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var mobile by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Sign up",
            style = MaterialTheme.typography.headlineMedium.copy(fontSize = 32.sp),
            modifier = Modifier.padding(bottom = 16.dp)
        )
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
        TextField(
            value = mobile,
            onValueChange = { mobile = it },
            label = { Text("Mobile") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                coroutineScope.launch {
                    val response = userRepository.register(User(0, name, email,mobile, password))
                    if (response.isSuccessful) {
                        navController.navigate("home")
                    } else {
                        // Handle error
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
        ) {
            Text("SIGN UP", color = Color.White)
        }
        Spacer(modifier = Modifier.height(16.dp))
        TextButton(onClick = { navController.navigate("login") }) {
            Text("Already have an account?", color = Color.Gray)
        }
        Spacer(modifier = Modifier.height(16.dp))
        TextButton(onClick = { /* Social account signup logic */ }) {
            Text("Or sign up with social account", color = Color.Gray)
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(onClick = { /* Google signup logic */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_google),
                    contentDescription = "Google signup"
                )
            }
        }
    }
}
