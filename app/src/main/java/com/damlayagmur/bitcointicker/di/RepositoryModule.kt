package com.damlayagmur.bitcointicker.di

import com.damlayagmur.bitcointicker.data.local.CoinDatabase
import com.damlayagmur.bitcointicker.data.remote.CoinService
import com.damlayagmur.bitcointicker.data.repository.CoinRepositoryImpl
import com.damlayagmur.bitcointicker.data.repository.LoginRepository
import com.damlayagmur.bitcointicker.data.repository.RegisterRepository
import com.damlayagmur.bitcointicker.domain.repository.CoinRepository
import com.google.firebase.auth.FirebaseAuth
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

    @Provides
    @Singleton
    fun provideRegisterRepository(firebaseAuth: FirebaseAuth): RegisterRepository {
        return RegisterRepository(firebaseAuth)
    }

    @Provides
    @Singleton
    fun provideLoginRepository(firebaseAuth: FirebaseAuth): LoginRepository {
        return LoginRepository(firebaseAuth)
    }
}