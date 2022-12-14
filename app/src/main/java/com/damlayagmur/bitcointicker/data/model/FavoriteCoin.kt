package com.damlayagmur.bitcointicker.data.model

data class FavoriteCoin(
    var coinId: String? = "",
    val name: String? = "",
    val symbol: String? = "",
    val img: String? = "",
    val currentPrice: Double? = 0.0
)