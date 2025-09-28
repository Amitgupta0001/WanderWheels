package com.example.wanderwheels.data.repository

import com.example.wanderwheels.data.database.dao.CaravanDao
import com.example.wanderwheels.data.models.Caravan
import com.example.wanderwheels.data.models.LocalCaravan
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class CaravanRepository @Inject constructor(
    private val caravanDao: CaravanDao
) {
    private val db = Firebase.firestore
    private val gson = Gson()

    // Convert Firestore Caravan to LocalCaravan
    private fun Caravan.toLocalCaravan(): LocalCaravan {
        return LocalCaravan(
            id = this.id,
            title = this.title,
            description = this.description,
            pricePerNight = this.pricePerNight,
            location = this.location,
            latitude = this.latitude,
            longitude = this.longitude,
            images = gson.toJson(this.images),
            amenities = gson.toJson(this.amenities),
            isGuestFavorite = this.isGuestFavorite,
            hostId = this.hostId,
            hostName = this.hostName,
            hostImage = this.hostImage,
            rating = this.rating,
            reviewCount = this.reviewCount,
            maxGuests = this.maxGuests,
            bedrooms = this.bedrooms,
            beds = this.beds,
            bathrooms = this.bathrooms,
            vehicleType = this.vehicleType,
            year = this.year,
            features = gson.toJson(this.features)
        )
    }

    // Convert LocalCaravan to Caravan
    private fun LocalCaravan.toCaravan(): Caravan {
        return Caravan(
            id = this.id,
            title = this.title,
            description = this.description,
            pricePerNight = this.pricePerNight,
            location = this.location,
            latitude = this.latitude,
            longitude = this.longitude,
            images = gson.fromJson(this.images, Array<String>::class.java).toList(),
            amenities = gson.fromJson(this.amenities, Array<String>::class.java).toList(),
            isGuestFavorite = this.isGuestFavorite,
            hostId = this.hostId,
            hostName = this.hostName,
            hostImage = this.hostImage,
            rating = this.rating,
            reviewCount = this.reviewCount,
            maxGuests = this.maxGuests,
            bedrooms = this.bedrooms,
            beds = this.beds,
            bathrooms = this.bathrooms,
            vehicleType = this.vehicleType,
            year = this.year,
            features = gson.fromJson(this.features, Array<String>::class.java).toList()
        )
    }

    // Get caravans from local database first, then sync with Firebase
    fun getCaravans(): Flow<List<Caravan>> {
        // First return local data immediately
        return caravanDao.getAllCaravans().map { localCaravans ->
            localCaravans.map { it.toCaravan() }
        }
    }

    // Sync caravans from Firebase to local database
    suspend fun syncCaravansFromFirebase() {
        try {
            val caravans = db.collection("caravans").get().await().toObjects(Caravan::class.java)
            val localCaravans = caravans.map { it.toLocalCaravan() }
            caravanDao.insertAllCaravans(localCaravans)
        } catch (e: Exception) {
            // Handle error - app will use local data
        }
    }

    suspend fun getCaravanById(id: String): Caravan? {
        // Try local first
        val localCaravan = caravanDao.getCaravanById(id)
        if (localCaravan != null) {
            return localCaravan.toCaravan()
        }

        // If not found locally, try Firebase
        return try {
            val caravan = db.collection("caravans").document(id).get().await().toObject(Caravan::class.java)
            if (caravan != null) {
                // Save to local database for next time
                caravanDao.insertCaravan(caravan.toLocalCaravan())
            }
            caravan
        } catch (e: Exception) {
            null
        }
    }

    // Get favorite caravans from local database
    fun getFavoriteCaravans(): Flow<List<Caravan>> {
        return caravanDao.getFavoriteCaravans().map { localCaravans ->
            localCaravans.map { it.toCaravan() }
        }
    }

    // Search caravans locally
    fun searchCaravans(query: String): Flow<List<Caravan>> {
        return caravanDao.searchCaravans(query).map { localCaravans ->
            localCaravans.map { it.toCaravan() }
        }
    }
}