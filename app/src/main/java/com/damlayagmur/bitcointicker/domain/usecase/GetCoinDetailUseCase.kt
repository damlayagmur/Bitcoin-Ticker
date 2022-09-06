package com.damlayagmur.bitcointicker.domain.usecase

import com.damlayagmur.bitcointicker.common.Resource
import com.damlayagmur.bitcointicker.data.model.detail.CoinDetailModel
import com.damlayagmur.bitcointicker.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCoinDetailUseCase @Inject constructor(
    private val coinRepository: CoinRepository,
) {
    suspend operator fun invoke(id: String): Flow<Resource<CoinDetailModel>> =
        coinRepository.getCoinDetail(id)
}