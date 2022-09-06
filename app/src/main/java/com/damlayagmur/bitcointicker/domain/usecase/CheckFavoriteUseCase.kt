package com.damlayagmur.bitcointicker.domain.usecase

import com.damlayagmur.bitcointicker.data.repository.FirestoreRepository
import javax.inject.Inject

class CheckFavoriteUseCase @Inject constructor(
    private val firestoreRepository: FirestoreRepository
) {
    operator fun invoke(coinId: String) =
        firestoreRepository.checkFavourite(coinId)
}