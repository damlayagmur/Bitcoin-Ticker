package com.damlayagmur.bitcointicker.domain.usecase

import com.damlayagmur.bitcointicker.data.model.FavoriteCoin
import com.damlayagmur.bitcointicker.data.repository.FirestoreRepository
import javax.inject.Inject

class AddFavoriteUseCase @Inject constructor(
    private val firestoreRepository: FirestoreRepository
) {
    suspend operator fun invoke(favoriteCoin: FavoriteCoin) =
        firestoreRepository.addFavourite(favoriteCoin)
}