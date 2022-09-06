package com.damlayagmur.bitcointicker.data.model.detail

data class MarketData(
    var current_price: CurrentPrice,
    var price_change_percentage_24h: Double,
)