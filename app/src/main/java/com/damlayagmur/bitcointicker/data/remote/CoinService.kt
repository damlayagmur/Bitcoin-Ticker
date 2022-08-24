package com.damlayagmur.bitcointicker.data.remote

import com.damlayagmur.bitcointicker.data.model.Coin
import retrofit2.http.GET

interface CoinService {
    @GET("coins/list")
    suspend fun getCoinList(): List<Coin>
}