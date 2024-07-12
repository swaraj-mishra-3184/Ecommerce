package com.lucky.ecommmerce.data.repository

import com.lucky.ecommmerce.data.api.ApiService
import com.lucky.ecommmerce.data.model.Product
import retrofit2.Response

class ProductRepository(private val apiService: ApiService) {
    suspend fun getAllProducts(): Response<List<Product>> = apiService.getAllProducts()
    suspend fun getCategories(): Response<List<String>> = apiService.getCategories()
}
