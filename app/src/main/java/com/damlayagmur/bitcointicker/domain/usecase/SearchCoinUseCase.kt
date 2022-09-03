package com.damlayagmur.bitcointicker.domain.usecase

import com.damlayagmur.bitcointicker.common.Resource
import com.damlayagmur.bitcointicker.data.model.CoinItem
import com.damlayagmur.bitcointicker.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchCoinUseCase @Inject constructor(
    private val coinRepository: CoinRepository,
) {
    suspend operator fun invoke(text: String): Flow<Resource<List<CoinItem>>> = coinRepository.searchCoin(text)
}