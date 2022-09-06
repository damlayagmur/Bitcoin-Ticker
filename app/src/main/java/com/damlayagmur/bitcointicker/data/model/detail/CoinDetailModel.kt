package com.damlayagmur.bitcointicker.data.model.detail

data class CoinDetailModel(
    var id: String,
    var symbol: String,
    var name: String,
    var description: Description,
    var hashing_algorithm: String,
    var market_data: MarketData,
    var image: Image,
)