package com.damlayagmur.bitcointicker.domain.usecase

import com.damlayagmur.bitcointicker.data.repository.FirestoreRepository
import javax.inject.Inject

class GetFavoritesUseCase @Inject constructor(
    private val firestoreRepository: FirestoreRepository
) {
    suspend operator fun invoke() =
        firestoreRepository.getFavourites()
}