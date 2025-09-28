package com.example.wanderwheels.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wanderwheels.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private val _isAuthenticated = MutableStateFlow(repository.hasUser())
    val isAuthenticated: StateFlow<Boolean> = _isAuthenticated.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    fun signUp(name: String, email: String, password: String, dateOfBirth: String) {
        _isLoading.value = true
        _errorMessage.value = null

        viewModelScope.launch {
            val result = repository.signUp(name, email, password, dateOfBirth)
            _isLoading.value = false

            if (result.isSuccess) {
                _isAuthenticated.value = true
            } else {
                _errorMessage.value = result.exceptionOrNull()?.message ?: "Sign up failed"
            }
        }
    }

    fun login(email: String, password: String) {
        _isLoading.value = true
        _errorMessage.value = null

        viewModelScope.launch {
            val result = repository.login(email, password)
            _isLoading.value = false

            if (result.isSuccess) {
                _isAuthenticated.value = true
            } else {
                _errorMessage.value = result.exceptionOrNull()?.message ?: "Login failed"
            }
        }
    }

    fun signInWithGoogle(idToken: String) {
        _isLoading.value = true
        _errorMessage.value = null

        viewModelScope.launch {
            val result = repository.signInWithGoogle(idToken)
            _isLoading.value = false

            if (result.isSuccess) {
                _isAuthenticated.value = true
            } else {
                _errorMessage.value = result.exceptionOrNull()?.message ?: "Google sign in failed"
            }
        }
    }

    fun logout() {
        repository.logout()
        _isAuthenticated.value = false
    }

    fun clearError() {
        _errorMessage.value = null
    }
}