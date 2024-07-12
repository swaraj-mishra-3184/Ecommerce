package com.lucky.ecommmerce.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.lucky.ecommmerce.data.model.LoginRequest
import com.lucky.ecommmerce.data.model.LoginResponse
import com.lucky.ecommmerce.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {
    private val _loginSuccess = MutableStateFlow<Boolean>(false)
    val loginSuccess: StateFlow<Boolean> get() = _loginSuccess

    private val _loginResponse = MutableStateFlow<LoginResponse?>(null)
    val loginResponse: StateFlow<LoginResponse?> get() = _loginResponse

    fun login(email: String, password: String) {
        viewModelScope.launch {
            val response = userRepository.login(LoginRequest(email, password))
            if (response.isSuccessful) {
                _loginSuccess.value = true
                _loginResponse.value = response.body()
            } else {
                _loginSuccess.value = false
                _loginResponse.value = null
                // Handle error
            }
        }
    }
}

class LoginViewModelFactory(private val userRepository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(userRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}