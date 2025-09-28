package com.example.wanderwheels.data.models

data class Caravan(
    val id: String = "",
    val title: String = "",
    val description: String = "",
    val pricePerNight: Double = 0.0,
    val location: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val images: List<String> = emptyList(),
    val amenities: List<String> = emptyList(),
    val isGuestFavorite: Boolean = false,
    val hostId: String = "",
    val hostName: String = "",
    val hostImage: String = "",
    val rating: Double = 0.0,
    val reviewCount: Int = 0,
    val maxGuests: Int = 2,
    val bedrooms: Int = 1,
    val beds: Int = 1,
    val bathrooms: Int = 1,
    val vehicleType: String = "Caravan",
    val year: Int = 2020,
    val features: List<String> = emptyList()
)