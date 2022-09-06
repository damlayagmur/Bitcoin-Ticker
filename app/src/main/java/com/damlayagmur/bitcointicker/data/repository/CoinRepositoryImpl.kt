package com.damlayagmur.bitcointicker.data.repository

import com.blankj.utilcode.util.LogUtils
import com.damlayagmur.bitcointicker.common.Resource
import com.damlayagmur.bitcointicker.data.local.CoinDao
import com.damlayagmur.bitcointicker.data.model.CoinItem
import com.damlayagmur.bitcointicker.data.model.detail.CoinDetailModel
import com.damlayagmur.bitcointicker.data.remote.CoinService
import com.damlayagmur.bitcointicker.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val service: CoinService,
    private val dao: CoinDao,
) : CoinRepository {

    /**
     * Get all coins from API and refresh data in local db
     */
    override suspend fun getCoinList(): Flow<Resource<List<CoinItem>>> = flow {
        emit(Resource.Loading())
        try {
            val coins = service.getCoinList()
            dao.deleteAll()
            dao.insertAll(coins.map { it.toItem() })
            emit(Resource.Success(coins))
        } catch (e: HttpException) {
            LogUtils.d("$this ${e.stackTrace}")
            emit(
                Resource.Error(
                    errorMessage = "Something went wrong!",
                    data = null
                )
            )
        } catch (e: IOException) {
            LogUtils.d("$this ${e.stackTrace}")
            Resource.Error(
                errorMessage = "Couldn't reach server,check your internet connection",
                data = null
            )
        }
    }

    /**
     * Search coin via text
     */
    override suspend fun searchCoin(text: String): Flow<Resource<List<CoinItem>>> = flow {
        emit(Resource.Loading())
        try {
            val coins = dao.search(text)
            emit(Resource.Success(coins.map { it.toItem() }))
        } catch (e: HttpException) {
            LogUtils.d("$this ${e.stackTrace}")
            emit(
                Resource.Error(
                    errorMessage = "Something went wrong!",
                    data = null
                )
            )
        } catch (e: IOException) {
            LogUtils.d("$this ${e.stackTrace}")
            Resource.Error(
                errorMessage = "Couldn't reach server,check your internet connection",
                data = null
            )
        }
    }

    /**
     * Get coin info by id from API
     */
    override suspend fun getCoinDetail(id: String): Flow<Resource<CoinDetailModel>> = flow {
        emit(Resource.Loading())
        try {
            val coinDetail = service.getCoinDetail(id)
            emit(Resource.Success(coinDetail))
        } catch (e: HttpException) {
            LogUtils.d("$this ${e.stackTrace}")
            emit(
                Resource.Error(
                    errorMessage = "Something went wrong!",
                    data = null
                )
            )
        } catch (e: IOException) {
            LogUtils.d("$this ${e.stackTrace}")
            Resource.Error(
                errorMessage = "Couldn't reach server,check your internet connection",
                data = null
            )
        }
    }
}