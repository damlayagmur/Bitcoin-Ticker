package com.damlayagmur.bitcointicker.domain.repository

import com.damlayagmur.bitcointicker.common.Resource
import com.damlayagmur.bitcointicker.data.model.CoinDetail
import com.damlayagmur.bitcointicker.data.model.CoinItem
import kotlinx.coroutines.flow.Flow

interface CoinRepository {

    suspend fun getCoinList(): Flow<Resource<List<CoinItem>>>

    suspend fun searchCoin(text: String): Flow<Resource<List<CoinItem>>>

    suspend fun getCoinDetail(id: String): Flow<Resource<CoinDetail>>
}