package com.lucky.ecommmerce.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.lucky.ecommmerce.ui.components.SearchBar
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.lucky.ecommmerce.R
import com.lucky.ecommmerce.ui.components.BottomNavigationBar
import com.lucky.ecommmerce.viewmodels.HomeViewModel
import com.lucky.ecommmerce.viewmodels.HomeViewModelFactory

@Composable
fun HomeScreen(navController: NavController) {
    val viewModel: HomeViewModel = viewModel(
        factory = HomeViewModelFactory()
    )

    var query by remember { mutableStateOf("") }
    val products = viewModel.products.collectAsState().value

    Scaffold(
        topBar = {
            SearchBar(
                query = query,
                onQueryChanged = { query = it },
                onSearch = { /* Perform search */ }
            )
        },
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .requiredHeight(intrinsicSize = IntrinsicSize.Min)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.promo_image), // Add your promo image here
                    contentDescription = "Fashion Sale",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.fillMaxSize()
                )
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Fashion sale",
                        color = Color.White,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = { /* Handle check action */ },
                        colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Red,
                                contentColor = Color.White,
                                disabledContentColor = Color.LightGray,
                                disabledContainerColor = Color.Red),
                        shape = RoundedCornerShape(50)
                    ) {
                        Text(text = "Check", color = Color.White)
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text("New", style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.Bold)
            Text("You've never seen it before!", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
            Spacer(modifier = Modifier.height(8.dp))
            // Add more UI components to display products
        }
    }
}

