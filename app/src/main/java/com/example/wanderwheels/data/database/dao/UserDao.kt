package com.example.wanderwheels.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.wanderwheels.data.models.LocalUser

@Dao
interface UserDao {

    @Query("SELECT * FROM users WHERE id = :userId")
    suspend fun getUserById(userId: String): LocalUser?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: LocalUser)

    @Query("DELETE FROM users WHERE id = :userId")
    suspend fun deleteUser(userId: String)
}