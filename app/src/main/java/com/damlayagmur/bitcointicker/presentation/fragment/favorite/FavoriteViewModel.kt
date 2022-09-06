package com.damlayagmur.bitcointicker.presentation.fragment.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.damlayagmur.bitcointicker.common.Resource
import com.damlayagmur.bitcointicker.data.model.FavoriteCoin
import com.damlayagmur.bitcointicker.domain.usecase.GetFavoritesUseCase
import com.damlayagmur.bitcointicker.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getFavoritesUseCase: GetFavoritesUseCase
) : BaseViewModel() {

    private val _favCoins = MutableLiveData<Resource<List<FavoriteCoin>?>>()
    val favCoins: LiveData<Resource<List<FavoriteCoin>?>>
        get() = _favCoins

    init {
        getFavorites()
    }

    fun getFavorites() = viewModelScope.launch {
        getFavoritesUseCase.invoke().collect {
            when (it) {
                is Resource.Loading -> {
                    _favCoins.value = Resource.Loading()
                }
                is Resource.Success -> {
                    _favCoins.value = Resource.Success(it.data)
                }
                is Resource.Error -> {
                    _favCoins.value = Resource.Error(it.errorMessage)
                }
            }
        }
    }

}