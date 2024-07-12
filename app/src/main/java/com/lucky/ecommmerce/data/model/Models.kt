package com.lucky.ecommmerce.data.model

// User.kt
data class User(
    val id: Int,
    val name: String,
    val email: String,
    val mobile: String,
    val password: String
)


data class LoginRequest(
    val email: String,
    val password: String
)


data class LoginResponse(
    val token: String,
    val user: User
)


data class Product(
    val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val category: String,
    val image: String,
    val discountPercentage: Double,
    val rating: Rating
)

data class Rating(
    val rate: Double,
    val count: Int
)

data class Order(
    val id: Int,
    val userId: Int,
    val productId: Int,
    val quantity: Int,
    val address: String,
    val status: String
)
