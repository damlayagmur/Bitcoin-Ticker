package com.damlayagmur.bitcointicker.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [CoinEntity::class],
    version = 1
)
abstract class CoinDatabase : RoomDatabase() {

    abstract val dao: CoinDao
}