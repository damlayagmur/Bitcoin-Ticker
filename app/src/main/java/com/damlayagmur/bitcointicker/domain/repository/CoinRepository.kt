package com.damlayagmur.bitcointicker.domain.repository

import com.damlayagmur.bitcointicker.data.model.Coin
import com.damlayagmur.bitcointicker.data.model.CoinItem

interface CoinRepository {

    suspend fun getCoinList(): List<CoinItem>
}