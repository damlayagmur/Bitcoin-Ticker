package com.damlayagmur.bitcointicker.data.repository

import com.blankj.utilcode.util.LogUtils
import com.damlayagmur.bitcointicker.common.Constants.COINS
import com.damlayagmur.bitcointicker.common.Constants.FAVORITES
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

    /**
     * Add coin to favorite list
     *
     * @param favoriteCoin as [FavoriteCoin]
     */
    fun addFavourite(favoriteCoin: FavoriteCoin) = flow {
        emit(Resource.Loading())
        firebaseAuth.currentUser?.uid?.let {
            val coinRef = firebaseFirestore.collection(FAVORITES).document(it)
                .collection(COINS).document(favoriteCoin.coinId ?: "")
                .set(favoriteCoin).await()

            emit(Resource.Success(coinRef))
        }
    }.catch {
        LogUtils.d("$this ${it.stackTrace}")
        emit(Resource.Error(it.message ?: ""))
    }

    /**
     * Check if the coin is in the favorite list
     */
    fun checkFavourite(coinId: String) = flow {
        emit(Resource.Loading())
        firebaseAuth.currentUser?.uid?.let {
            val snapshot = firebaseFirestore.collection(FAVORITES).document(it)
                .collection(COINS).get().await()

            val data = snapshot.toObjects(FavoriteCoin::class.java)
            data.let {
                val coin = data.find { it.coinId == coinId }
                if (coin == null)
                    emit(Resource.Success(false))
                else
                    emit(Resource.Success(true))
            }
        }
    }.catch {
        LogUtils.d("$this ${it.stackTrace}")
        emit(Resource.Error(it.message ?: ""))
    }

    /**
     * Get favorite list
     */
    fun getFavourites() = flow {
        emit(Resource.Loading())
        firebaseAuth.currentUser?.uid?.let {
            val snapshot = firebaseFirestore.collection(FAVORITES).document(it)
                .collection(COINS).get().await()

            val data = snapshot.toObjects(FavoriteCoin::class.java)
            emit(Resource.Success(data))
        }
    }.catch {
        LogUtils.d("$this ${it.stackTrace}")
        emit(Resource.Error(it.message ?: ""))
    }

    /**
     * Delete coin from favorite list
     */
    fun deleteFavourites(coinId: String) = flow {
        emit(Resource.Loading())
        firebaseAuth.currentUser?.uid?.let {
            val coinRef = firebaseFirestore.collection(FAVORITES).document(it)
                .collection(COINS).document(coinId).delete()
            coinRef.await()

            emit(Resource.Success(coinRef))
        }
    }.catch {
        LogUtils.d("$this ${it.stackTrace}")
        emit(Resource.Error(it.message ?: ""))
    }
}