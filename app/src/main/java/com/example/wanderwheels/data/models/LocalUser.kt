package com.example.wanderwheels.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class LocalUser(
    @PrimaryKey
    val id: String,
    val name: String,
    val email: String,
    val dateOfBirth: String,
    val profileImage: String,
    val phoneNumber: String,
    val createdAt: Long
)