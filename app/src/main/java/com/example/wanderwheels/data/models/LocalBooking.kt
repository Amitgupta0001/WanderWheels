package com.example.wanderwheels.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookings")
data class LocalBooking(
    @PrimaryKey
    val id: String,
    val userId: String,
    val caravanId: String,
    val startDate: String,
    val endDate: String,
    val totalPrice: Double,
    val status: String,
    val createdAt: Long,
    val guests: Int
)