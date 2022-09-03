package com.damlayagmur.bitcointicker.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CoinDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(infos: List<CoinEntity>)

    @Query("DELETE FROM coinentity")
    suspend fun deleteAll()

    @Query("SELECT * FROM coinentity WHERE symbol LIKE '%' || :word || '%' OR name LIKE '%' || :word")
    suspend fun search(word: String): List<CoinEntity>
}