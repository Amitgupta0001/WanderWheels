package com.example.wanderwheels.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.wanderwheels.data.database.dao.BookingDao
import com.example.wanderwheels.data.database.dao.CaravanDao
import com.example.wanderwheels.data.database.dao.UserDao
import com.example.wanderwheels.data.database.dao.WishlistDao
import com.example.wanderwheels.data.models.LocalBooking
import com.example.wanderwheels.data.models.LocalCaravan
import com.example.wanderwheels.data.models.LocalUser
import com.example.wanderwheels.data.models.LocalWishlist

@Database(
    entities = [
        LocalCaravan::class,
        LocalUser::class,
        LocalBooking::class,
        LocalWishlist::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class WanderWheelsDatabase : RoomDatabase() {

    abstract fun caravanDao(): CaravanDao
    abstract fun userDao(): UserDao
    abstract fun bookingDao(): BookingDao
    abstract fun wishlistDao(): WishlistDao

    companion object {
        @Volatile
        private var INSTANCE: WanderWheelsDatabase? = null

        fun getInstance(context: Context): WanderWheelsDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WanderWheelsDatabase::class.java,
                    "wanderwheels_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}