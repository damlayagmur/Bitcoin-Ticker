package com.damlayagmur.bitcointicker.data.model

import com.damlayagmur.bitcointicker.data.local.CoinEntity

data class CoinItem(
    val id: String,
    val name: String,
    val symbol: String
) {
    fun toItem(): CoinEntity {
        return CoinEntity(
            id = id,
            symbol = symbol,
            name = name
        )
    }
}