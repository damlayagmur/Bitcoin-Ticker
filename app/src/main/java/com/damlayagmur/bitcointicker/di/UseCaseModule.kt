package com.damlayagmur.bitcointicker.di

import com.damlayagmur.bitcointicker.domain.repository.CoinRepository
import com.damlayagmur.bitcointicker.data.repository.LoginRepository
import com.damlayagmur.bitcointicker.data.repository.RegisterRepository
import com.damlayagmur.bitcointicker.domain.usecase.GetCoinListUseCase
import com.damlayagmur.bitcointicker.domain.usecase.LoginUseCase
import com.damlayagmur.bitcointicker.domain.usecase.RegisterUseCase
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

    @Provides
    @Singleton
    fun provideRegisterUseCase(
        registerRepository: RegisterRepository,
    ) = RegisterUseCase(registerRepository)

    @Provides
    @Singleton
    fun provideLoginUseCase(
        loginRepository: LoginRepository,
    ) = LoginUseCase(loginRepository)
}