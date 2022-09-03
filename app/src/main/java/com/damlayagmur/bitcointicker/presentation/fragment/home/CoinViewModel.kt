package com.damlayagmur.bitcointicker.presentation.fragment.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.damlayagmur.bitcointicker.common.Resource
import com.damlayagmur.bitcointicker.data.model.CoinItem
import com.damlayagmur.bitcointicker.domain.usecase.GetCoinListUseCase
import com.damlayagmur.bitcointicker.domain.usecase.SearchCoinUseCase
import com.damlayagmur.bitcointicker.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinViewModel @Inject constructor(
    private val coinUseCase: GetCoinListUseCase,
    private val searchCoinUseCase: SearchCoinUseCase
) : BaseViewModel() {

    private val _coinList = MutableLiveData<Resource<List<CoinItem?>>>()
    val coinListLiveData: LiveData<Resource<List<CoinItem?>>>
        get() = _coinList

    init {
        getCoinList()
    }

    private fun getCoinList() {
        viewModelScope.launch {
            coinUseCase.invoke().collect {
                when (it) {
                    is Resource.Loading -> {
                        _coinList.value = Resource.Loading()
                    }
                    is Resource.Success -> {
                        _coinList.value = Resource.Success(it.data!!)
                    }
                    is Resource.Error -> {
                        _coinList.value =
                            it.errorMessage?.let { message -> Resource.Error(message) }
                    }
                }
            }
        }
    }

    fun searchCoin(text: String) {
        viewModelScope.launch {
            searchCoinUseCase.invoke(text).collect {
                when (it) {
                    is Resource.Loading -> {
                        _coinList.value = Resource.Loading()
                    }
                    is Resource.Success -> {
                        _coinList.value = Resource.Success(it.data!!)
                    }
                    is Resource.Error -> {
                        _coinList.value =
                            it.errorMessage?.let { message -> Resource.Error(message) }
                    }
                }
            }
        }
    }
}