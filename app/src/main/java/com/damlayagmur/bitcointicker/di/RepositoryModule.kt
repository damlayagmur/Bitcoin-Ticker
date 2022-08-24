package com.damlayagmur.bitcointicker.di

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
    fun provideMovieRepository(service: CoinService): CoinRepository {
        return CoinRepositoryImpl(service)
    }
}