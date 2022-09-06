package com.damlayagmur.bitcointicker.domain.usecase

import com.damlayagmur.bitcointicker.data.repository.FirestoreRepository
import javax.inject.Inject

class DeleteFavoriteUseCase @Inject constructor(
    private val firestoreRepository: FirestoreRepository
) {
    suspend operator fun invoke(coinId: String) =
        firestoreRepository.deleteFavourites(coinId)
}