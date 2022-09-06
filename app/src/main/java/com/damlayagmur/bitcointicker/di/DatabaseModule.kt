package com.damlayagmur.bitcointicker.di

import android.app.Application
import androidx.room.Room
import com.damlayagmur.bitcointicker.data.local.CoinDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideCoinDao(app: Application): CoinDatabase {
        return Room.databaseBuilder(
            app, CoinDatabase::class.java, "coin_db"
        ).build()
    }
}