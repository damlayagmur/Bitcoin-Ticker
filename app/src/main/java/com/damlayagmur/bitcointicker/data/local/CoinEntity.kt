package com.damlayagmur.bitcointicker.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.damlayagmur.bitcointicker.data.model.CoinItem

@Entity
data class CoinEntity(
    @PrimaryKey val id: String,
    val symbol: String,
    val name: String,
){
    fun toItem(): CoinItem{
        return CoinItem(
            id = id,
            symbol = symbol,
            name = name,
            //sourceUrls = sourceUrl
        )
    }
}