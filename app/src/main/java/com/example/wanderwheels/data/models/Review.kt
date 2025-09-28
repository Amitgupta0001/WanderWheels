package com.example.wanderwheels.data.models

data class Review(
    val id: String = "",
    val userId: String = "",
    val userName: String = "",
    val userImage: String = "",
    val rating: Double = 0.0,
    val comment: String = "",
    val date: String = "",
    val caravanId: String = ""
)