package com.example.wanderwheels.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.wanderwheels.data.models.LocalCaravan
import kotlinx.coroutines.flow.Flow

@Dao
interface CaravanDao {

    @Query("SELECT * FROM caravans ORDER BY lastUpdated DESC")
    fun getAllCaravans(): Flow<List<LocalCaravan>>

    @Query("SELECT * FROM caravans WHERE id = :caravanId")
    suspend fun getCaravanById(caravanId: String): LocalCaravan?

    @Query("SELECT * FROM caravans WHERE isGuestFavorite = 1 ORDER BY rating DESC")
    fun getFavoriteCaravans(): Flow<List<LocalCaravan>>

    @Query("SELECT * FROM caravans WHERE location LIKE '%' || :query || '%' OR title LIKE '%' || :query || '%'")
    fun searchCaravans(query: String): Flow<List<LocalCaravan>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCaravan(caravan: LocalCaravan)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCaravans(caravans: List<LocalCaravan>)

    @Query("DELETE FROM caravans WHERE id = :caravanId")
    suspend fun deleteCaravan(caravanId: String)

    @Query("DELETE FROM caravans")
    suspend fun clearAllCaravans()
}