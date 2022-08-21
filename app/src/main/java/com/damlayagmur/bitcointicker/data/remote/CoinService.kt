package com.damlayagmur.bitcointicker.data.remote

import retrofit2.http.GET

interface CoinService {
    @GET()
    suspend fun getCoinList(){

    }
}