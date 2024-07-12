package com.lucky.ecommmerce.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.lucky.ecommmerce.data.api.ApiServiceInstance
import com.lucky.ecommmerce.data.model.Product
import com.lucky.ecommmerce.data.repository.ProductRepository
import kotlinx.coroutines.launch

@Composable
fun ProductListScreen(navController: NavController, category: String) {
    val apiService = ApiServiceInstance.apiService
    val productRepository = ProductRepository(apiService)
    var products by remember { mutableStateOf(emptyList<Product>()) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            val response = productRepository.getAllProducts()
            if (response.isSuccessful) {
                products = response.body()?.filter { it.category == category } ?: emptyList()
            } else {
                // Handle error
            }
        }
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFFF5F5F5))) {
        if (products.isNotEmpty()) {
            val headerProduct = products.first()
            // Header with the first product's image
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(headerProduct.image)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            Text(
                text = "${category}",
                style = MaterialTheme.typography.headlineLarge.copy(
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Cursive),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .offset(y = (-40).dp)
                    .background(Color.Transparent)
                    .shadow(7.dp)
                    .padding(3.dp)
            )
        }

        // New Products Section
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            Text(
                text = "New",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(vertical = 8.dp),
                fontFamily = FontFamily.Cursive
            )
            Text(
                text = "You've never seen it before!",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(products) { product ->
                    ProductCard(product = product)
                }
                item {
                    Icon(
                        modifier = Modifier.align(Alignment.CenterHorizontally).size(25.dp),
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = "More",
                        tint = Color.LightGray
                    )
                }
            }
        }
    }
}

@Composable
fun ProductCard(product: Product) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .padding(8.dp)
            .width(150.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(product.image)
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(120.dp)
                .clip(RoundedCornerShape(8.dp))
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = product.title,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(horizontal = 8.dp),
            fontFamily = FontFamily.SansSerif
        )
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {
            repeat(product.rating.rate.toInt()) {
                Icon(
                    imageVector = Icons.Default.Star, // Replace with your star icon
                    contentDescription = "Star",
                    tint = Color.Yellow,
                    modifier = Modifier.size(16.dp)
                )
            }
            Text(
                text = "${product.price} USD",
                style = MaterialTheme.typography.bodySmall.copy(color = Color.Red, fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "${product.discountPercentage}%",
                style = MaterialTheme.typography.bodySmall.copy(color = Color.White)
            )
        }
    }
}
