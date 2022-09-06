package com.damlayagmur.bitcointicker.di

import com.damlayagmur.bitcointicker.data.repository.FirestoreRepository
import com.damlayagmur.bitcointicker.data.repository.LoginRepository
import com.damlayagmur.bitcointicker.data.repository.RegisterRepository
import com.damlayagmur.bitcointicker.domain.repository.CoinRepository
import com.damlayagmur.bitcointicker.domain.usecase.*
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

    @Provides
    @Singleton
    fun provideAddFavoriteUseCase(
        firestoreRepository: FirestoreRepository,
    ) = AddFavoriteUseCase(firestoreRepository)

    @Provides
    @Singleton
    fun provideCheckFavoriteUseCase(
        firestoreRepository: FirestoreRepository,
    ) = CheckFavoriteUseCase(firestoreRepository)

    @Provides
    @Singleton
    fun provideGetFavoritesUseCase(
        firestoreRepository: FirestoreRepository,
    ) = GetFavoritesUseCase(firestoreRepository)

    @Provides
    @Singleton
    fun provideDeleteFavoriteUseCase(
        firestoreRepository: FirestoreRepository,
    ) = DeleteFavoriteUseCase(firestoreRepository)
}