package com.damlayagmur.bitcointicker.di

import com.damlayagmur.bitcointicker.data.local.CoinDatabase
import com.damlayagmur.bitcointicker.data.remote.CoinService
import com.damlayagmur.bitcointicker.data.repository.CoinRepositoryImpl
import com.damlayagmur.bitcointicker.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideCoinRepository(service: CoinService, coindDatabase: CoinDatabase): CoinRepository {
        return CoinRepositoryImpl(service, coindDatabase.dao)
    }
}