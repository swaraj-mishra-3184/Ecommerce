package com.lucky.ecommmerce.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.lucky.ecommmerce.data.api.ApiServiceInstance
import com.lucky.ecommmerce.data.model.Product
import com.lucky.ecommmerce.data.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel() : ViewModel() {
    val apiService = ApiServiceInstance.apiService
    private val productRepository = ProductRepository(apiService)
    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> get() = _products

    init {
        fetchProducts()
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            val response = productRepository.getAllProducts()
            if (response.isSuccessful) {
                _products.value = response.body() ?: emptyList()
            } else {
                // Handle error
            }
        }
    }
}

class HomeViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}