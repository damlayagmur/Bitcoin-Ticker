package com.damlayagmur.bitcointicker.domain.usecase

import com.damlayagmur.bitcointicker.common.Resource
import com.damlayagmur.bitcointicker.data.model.detail.MarketData
import com.damlayagmur.bitcointicker.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCoinPriceUseCase @Inject constructor(
    private val coinRepository: CoinRepository,
) {
    suspend operator fun invoke(id: String): Flow<Resource<MarketData>> =
        coinRepository.getCoinPrice(id)
}