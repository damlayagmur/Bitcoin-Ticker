package com.damlayagmur.bitcointicker.data.repository

import com.damlayagmur.bitcointicker.common.Resource
import com.damlayagmur.bitcointicker.data.model.FavoriteCoin
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirestoreRepository @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore
) {

    fun addFavourite(favoriteCoin: FavoriteCoin) = flow {
        emit(Resource.Loading())
        firebaseAuth.currentUser?.uid?.let {
            val favRef = firebaseFirestore.collection("favorites").document(it)
                .collection("coins").document(favoriteCoin.coinId ?: "")
                .set(favoriteCoin)

            favRef.await()

            emit(Resource.Success(favRef))
        }
    }.catch {
        emit(Resource.Error(it.message ?: ""))
    }
}