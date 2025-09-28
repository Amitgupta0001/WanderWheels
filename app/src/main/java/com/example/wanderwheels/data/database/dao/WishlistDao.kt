package com.example.wanderwheels.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.wanderwheels.data.models.LocalWishlist
import kotlinx.coroutines.flow.Flow

@Dao
interface WishlistDao {

    @Query("SELECT c.* FROM caravans c INNER JOIN wishlists w ON c.id = w.caravanId WHERE w.userId = :userId ORDER BY w.addedAt DESC")
    fun getUserWishlist(userId: String): Flow<List<LocalCaravan>>

    @Query("SELECT * FROM wishlists WHERE userId = :userId AND caravanId = :caravanId")
    suspend fun isInWishlist(userId: String, caravanId: String): LocalWishlist?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToWishlist(wishlist: LocalWishlist)

    @Query("DELETE FROM wishlists WHERE userId = :userId AND caravanId = :caravanId")
    suspend fun removeFromWishlist(userId: String, caravanId: String)

    @Query("DELETE FROM wishlists WHERE userId = :userId")
    suspend fun clearUserWishlist(userId: String)
}