package com.damlayagmur.bitcointicker.data.repository

import com.damlayagmur.bitcointicker.data.model.Coin
import com.damlayagmur.bitcointicker.data.remote.CoinService
import com.damlayagmur.bitcointicker.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val service: CoinService,
) : CoinRepository {
    override suspend fun getCoinList(): List<Coin> {
        return service.getCoinList()
    }
}