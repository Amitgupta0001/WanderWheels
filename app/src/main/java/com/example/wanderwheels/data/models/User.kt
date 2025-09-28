package com.example.wanderwheels.data.models

data class User(
    val id: String = "",
    val name: String = "",
    val email: String = "",
    val dateOfBirth: String = "",
    val profileImage: String = "",
    val phoneNumber: String = "",
    val createdAt: Long = System.currentTimeMillis()
)