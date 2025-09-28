package com.example.wanderwheels.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wanderwheels.data.models.Caravan
import com.example.wanderwheels.data.models.Review
import com.example.wanderwheels.data.repository.CaravanRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CaravanViewModel @Inject constructor(
    private val repository: CaravanRepository
) : ViewModel() {

    private val _caravans = MutableStateFlow<List<Caravan>>(emptyList())
    val caravans: StateFlow<List<Caravan>> = _caravans.asStateFlow()

    private val _selectedCaravan = MutableStateFlow<Caravan?>(null)
    val selectedCaravan: StateFlow<Caravan?> = _selectedCaravan.asStateFlow()

    private val _reviews = MutableStateFlow<List<Review>>(emptyList())
    val reviews: StateFlow<List<Review>> = _reviews.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _isOffline = MutableStateFlow(false)
    val isOffline: StateFlow<Boolean> = _isOffline.asStateFlow()

    init {
        loadCaravans()
    }

    private fun loadCaravans() {
        viewModelScope.launch {
            _isLoading.value = true

            // First sync with Firebase
            try {
                repository.syncCaravansFromFirebase()
                _isOffline.value = false
            } catch (e: Exception) {
                _isOffline.value = true
                // Continue with local data
            }

            // Then observe local data
            repository.getCaravans().collect { caravansList ->
                _caravans.value = caravansList
                _isLoading.value = false
            }
        }
    }

    fun getCaravanDetails(caravanId: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val caravan = repository.getCaravanById(caravanId)
                _selectedCaravan.value = caravan
                _isLoading.value = false
            } catch (e: Exception) {
                _isLoading.value = false
            }
        }
    }

    fun refreshCaravans() {
        viewModelScope.launch {
            try {
                repository.syncCaravansFromFirebase()
                _isOffline.value = false
            } catch (e: Exception) {
                _isOffline.value = true
            }
        }
    }

    fun clearSelectedCaravan() {
        _selectedCaravan.value = null
        _reviews.value = emptyList()
    }
}