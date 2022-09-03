package com.damlayagmur.bitcointicker.presentation.fragment.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.damlayagmur.bitcointicker.common.Resource
import com.damlayagmur.bitcointicker.data.model.CoinDetail
import com.damlayagmur.bitcointicker.domain.usecase.GetCoinDetailUseCase
import com.damlayagmur.bitcointicker.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinDetailUseCase: GetCoinDetailUseCase
) : BaseViewModel() {

    private val _coinDetail = MutableLiveData<Resource<CoinDetail>>()
    val coinDetailLiveData: LiveData<Resource<CoinDetail>>
        get() = _coinDetail

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
}