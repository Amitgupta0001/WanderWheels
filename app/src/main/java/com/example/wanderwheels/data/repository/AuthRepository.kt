package com.example.wanderwheels.data.repository

import com.example.wanderwheels.data.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore
) {

    val currentUser get() = auth.currentUser

    fun hasUser(): Boolean = auth.currentUser != null

    suspend fun signUp(
        name: String,
        email: String,
        password: String,
        dateOfBirth: String
    ): Result<Boolean> {
        return try {
            val authResult = auth.createUserWithEmailAndPassword(email, password).await()
            val user = User(
                id = authResult.user?.uid ?: "",
                name = name,
                email = email,
                dateOfBirth = dateOfBirth
            )
            db.collection("users").document(user.id).set(user).await()
            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun login(email: String, password: String): Result<Boolean> {
        return try {
            auth.signInWithEmailAndPassword(email, password).await()
            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun signInWithGoogle(idToken: String): Result<Boolean> {
        return try {
            val credential = GoogleAuthProvider.getCredential(idToken, null)
            val authResult = auth.signInWithCredential(credential).await()
            val user = authResult.user

            if (user != null) {
                val firestoreUser = User(
                    id = user.uid,
                    name = user.displayName ?: "",
                    email = user.email ?: "",
                    profileImage = user.photoUrl?.toString() ?: ""
                )
                db.collection("users").document(user.uid).set(firestoreUser).await()
            }

            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun logout() {
        auth.signOut()
    }
}