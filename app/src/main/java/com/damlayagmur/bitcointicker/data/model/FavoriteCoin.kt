package com.damlayagmur.bitcointicker.data.model

data class FavoriteCoin(
    var coinId: String? = "",
    val name: String? = "",
    val description: String? = "",
    val img: String? = "",
    val currentPrice: Double? = 0.0
)