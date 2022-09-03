package com.damlayagmur.bitcointicker.data.remote

import com.damlayagmur.bitcointicker.data.model.CoinItem
import retrofit2.http.GET

interface CoinService {
    @GET("coins/list")
    suspend fun getCoinList(): List<CoinItem>
}