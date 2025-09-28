package com.example.wanderwheels.data.models

enum class BookingStatus {
    PENDING, CONFIRMED, COMPLETED, CANCELLED
}

data class Booking(
    val id: String = "",
    val userId: String = "",
    val caravanId: String = "",
    val startDate: String = "",
    val endDate: String = "",
    val totalPrice: Double = 0.0,
    val status: BookingStatus = BookingStatus.PENDING,
    val createdAt: Long = System.currentTimeMillis(),
    val guests: Int = 1
)