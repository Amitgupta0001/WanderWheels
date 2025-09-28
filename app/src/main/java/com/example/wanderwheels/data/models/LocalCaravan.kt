package com.example.wanderwheels.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "caravans")
data class LocalCaravan(
    @PrimaryKey
    val id: String,
    val title: String,
    val description: String,
    val pricePerNight: Double,
    val location: String,
    val latitude: Double,
    val longitude: Double,
    val images: String, // Store as JSON string
    val amenities: String, // Store as JSON string
    val isGuestFavorite: Boolean,
    val hostId: String,
    val hostName: String,
    val hostImage: String,
    val rating: Double,
    val reviewCount: Int,
    val maxGuests: Int,
    val bedrooms: Int,
    val beds: Int,
    val bathrooms: Int,
    val vehicleType: String,
    val year: Int,
    val features: String, // Store as JSON string
    val lastUpdated: Long = System.currentTimeMillis()
)