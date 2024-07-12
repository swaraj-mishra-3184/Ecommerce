package com.lucky.ecommmerce.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.lucky.ecommmerce.data.model.User
import com.lucky.ecommmerce.viewmodels.EditProfileViewModel

@Composable
fun EditProfileScreen(navController: NavController, viewModel: EditProfileViewModel = viewModel()) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var mobile by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        )
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        )
        TextField(
            value = mobile,
            onValueChange = { mobile = it },
            label = { Text("Mobile") },
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        )
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        )
        Button(onClick = {
            viewModel.updateUser(User(1, name, email, mobile, password))
            navController.navigate("profile")
        }) {
            Text("Save")
        }
    }
}
