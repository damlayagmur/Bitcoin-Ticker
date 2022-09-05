package com.damlayagmur.bitcointicker.presentation.fragment.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.damlayagmur.bitcointicker.common.Resource
import com.damlayagmur.bitcointicker.data.model.FavoriteCoin
import com.damlayagmur.bitcointicker.data.model.detail.CoinDetailModel
import com.damlayagmur.bitcointicker.domain.usecase.AddFavoriteUseCase
import com.damlayagmur.bitcointicker.domain.usecase.CheckFavoriteUseCase
import com.damlayagmur.bitcointicker.domain.usecase.GetCoinDetailUseCase
import com.damlayagmur.bitcointicker.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinDetailUseCase: GetCoinDetailUseCase,
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val checkFavoriteUseCase: CheckFavoriteUseCase,
) : BaseViewModel() {

    private val _coinDetail = MutableLiveData<Resource<CoinDetailModel>>()
    val coinDetailLiveData: LiveData<Resource<CoinDetailModel>>
        get() = _coinDetail

    private val _favStatus = MutableLiveData<Resource<Boolean?>>()
    val favStatus: LiveData<Resource<Boolean?>>
        get() = _favStatus

    fun getCoinDetail(coinId: String) {
        viewModelScope.launch {
            getCoinDetailUseCase.invoke(coinId).collect {
                when (it) {
                    is Resource.Loading -> {
                        _coinDetail.value = Resource.Loading()
                    }
                    is Resource.Success -> {
                        _coinDetail.value = Resource.Success(it.data!!)
                    }
                    is Resource.Error -> {
                        _coinDetail.value =
                            it.errorMessage?.let { message -> Resource.Error(message) }
                    }
                }
            }
        }
    }

    fun checkFavorite(coinId: String) = viewModelScope.launch {
        checkFavoriteUseCase.invoke(coinId).collect {
            when (it) {
                is Resource.Loading -> {
                    _favStatus.value = Resource.Loading()
                }
                is Resource.Success -> {
                    _favStatus.value = Resource.Success(it.data)
                }
                is Resource.Error -> {
                    _favStatus.value =
                        it.errorMessage?.let { message -> Resource.Error(message) }
                }
            }
        }
    }

    fun addFavorite() = viewModelScope.launch {
        val coin = _coinDetail.value?.data
        val favCoin = FavoriteCoin(
            coinId = coin?.id,
            name = coin?.name,
            description = coin?.description?.en,
            img = coin?.image?.large,
            currentPrice = coin?.market_data?.current_price?.usd
        )
        addFavoriteUseCase.invoke(favCoin).collect {
            when (it) {
                is Resource.Loading -> {
                    Log.d("TAG", "addFavorite: ")
                }
                is Resource.Success -> {
                    _favStatus.value = Resource.Success(true)
                }
                is Resource.Error -> {
                    Log.d("TAG", "addFavorite: ")
                }
            }
        }
    }
}