package com.lucky.ecommmerce.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.lucky.ecommmerce.R
import com.lucky.ecommmerce.data.SharedPreferencesManager
import com.lucky.ecommmerce.data.api.ApiServiceInstance
import com.lucky.ecommmerce.data.repository.UserRepository
import com.lucky.ecommmerce.viewmodels.LoginViewModel
import com.lucky.ecommmerce.viewmodels.LoginViewModelFactory

@Composable
fun LoginScreen(navController: NavController, context: Context) {
    val apiService = ApiServiceInstance.apiService
    val userRepository = UserRepository(apiService)
    val viewModel: LoginViewModel = viewModel(
        factory = LoginViewModelFactory(userRepository)
    )

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val sharedPreferencesManager = remember { SharedPreferencesManager(context) }
    val loginSuccess by viewModel.loginSuccess.collectAsState()

    LaunchedEffect(loginSuccess) {
        if (loginSuccess) {
            navController.navigate("home")
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Login",
            style = MaterialTheme.typography.headlineMedium.copy(fontSize = 32.sp),
            modifier = Modifier.padding(bottom = 16.dp)
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
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
        TextButton(onClick = { /* Forgot password logic */ }, modifier = Modifier.align(Alignment.End)) {
            Text("Forgot your password?", color = Color.Gray)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                viewModel.login(email, password)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
        ) {
            Text("LOGIN", color = Color.White)
        }
        TextButton(onClick = { navController.navigate("register") }) {
            Text("Don't have an account? Register")
        }
        Spacer(modifier = Modifier.height(16.dp))
        TextButton(onClick = { navController.navigate("register") }) {
            Text("Or login with social account ", color = Color.Gray)
            IconButton(onClick = { /* Google login logic */ },
                modifier = Modifier.size(18.dp)) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_google),
                    contentDescription = "Google login"

                )
            }
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {

        }
    }
}
