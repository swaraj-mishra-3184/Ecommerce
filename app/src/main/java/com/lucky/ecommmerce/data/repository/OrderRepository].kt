package com.lucky.ecommmerce.data.repository

import com.lucky.ecommmerce.data.api.ApiService
import com.lucky.ecommmerce.data.model.Order
import retrofit2.Response

class OrderRepository(private val apiService: ApiService) {
    suspend fun placeOrder(order: Order): Response<Order> = apiService.placeOrder(order)
}
