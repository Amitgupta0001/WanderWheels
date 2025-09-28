package com.example.wanderwheels.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.wanderwheels.data.models.LocalBooking
import kotlinx.coroutines.flow.Flow

@Dao
interface BookingDao {

    @Query("SELECT * FROM bookings WHERE userId = :userId ORDER BY createdAt DESC")
    fun getUserBookings(userId: String): Flow<List<LocalBooking>>

    @Query("SELECT * FROM bookings WHERE id = :bookingId")
    suspend fun getBookingById(bookingId: String): LocalBooking?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBooking(booking: LocalBooking)

    @Query("DELETE FROM bookings WHERE id = :bookingId")
    suspend fun deleteBooking(bookingId: String)

    @Query("DELETE FROM bookings WHERE userId = :userId")
    suspend fun clearUserBookings(userId: String)
}