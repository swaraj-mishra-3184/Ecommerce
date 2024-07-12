package com.lucky.ecommmerce.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lucky.ecommmerce.data.model.User
import com.lucky.ecommmerce.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EditProfileViewModel(private val userRepository: UserRepository) : ViewModel() {
    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> get() = _user

    fun updateUser(user: User) {
        viewModelScope.launch {
            val response = userRepository.updateUser(user.id, user)
            if (response.isSuccessful) {
                _user.value = user
            } else {
                // Handle error
            }
        }
    }
}
