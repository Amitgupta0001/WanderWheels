package com.example.wanderwheels.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wishlists")
data class LocalWishlist(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val userId: String,
    val caravanId: String,
    val addedAt: Long = System.currentTimeMillis()
)