package com.damlayagmur.bitcointicker.di

import com.damlayagmur.bitcointicker.domain.repository.CoinRepository
import com.damlayagmur.bitcointicker.domain.usecase.GetCoinListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideCoinUseCase(
        coinRepository: CoinRepository,
    ) = GetCoinListUseCase(coinRepository)
}