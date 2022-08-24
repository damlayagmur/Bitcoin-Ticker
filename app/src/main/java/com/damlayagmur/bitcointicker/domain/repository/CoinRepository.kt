package com.damlayagmur.bitcointicker.domain.repository

import com.damlayagmur.bitcointicker.data.model.Coin

interface CoinRepository {

    suspend fun getCoinList(): List<Coin>
}