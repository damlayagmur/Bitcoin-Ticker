package com.damlayagmur.bitcointicker.data.remote

import com.damlayagmur.bitcointicker.data.model.CoinDetail
import com.damlayagmur.bitcointicker.data.model.CoinItem
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinService {

    @GET("coins/list")
    suspend fun getCoinList(): List<CoinItem>

    @GET("/coins/{id}")
    suspend fun getCoinDetail(
        @Path("id") id: String
    ): CoinDetail
}