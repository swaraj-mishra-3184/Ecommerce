package com.lucky.ecommmerce.data.api

// ApiService.kt
import com.lucky.ecommmerce.data.model.LoginRequest
import com.lucky.ecommmerce.data.model.LoginResponse
import com.lucky.ecommmerce.data.model.Order
import com.lucky.ecommmerce.data.model.Product
import com.lucky.ecommmerce.data.model.User
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiService {
    @POST("users")
    suspend fun register(@Body user: User): Response<User>

    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @GET("products")
    suspend fun getAllProducts(): Response<List<Product>>

    @GET("products/categories")
    suspend fun getCategories(): Response<List<String>>

    @POST("orders")
    suspend fun placeOrder(@Body order: Order): Response<Order>

    @GET("users/{id}/orders")
    suspend fun getUserOrders(@Path("id") userId: Int): Response<List<Order>>

    @PUT("users/{id}")
    suspend fun updateUser(@Path("id") userId: Int, @Body user: User): Response<User>
}

object ApiServiceInstance {
    private const val BASE_URL = "https://fakestoreapi.com/"

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}