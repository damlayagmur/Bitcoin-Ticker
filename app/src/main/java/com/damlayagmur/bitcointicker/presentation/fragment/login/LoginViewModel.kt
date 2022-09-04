package com.damlayagmur.bitcointicker.presentation.fragment.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.damlayagmur.bitcointicker.common.EmailAuthResult
import com.damlayagmur.bitcointicker.common.Resource
import com.damlayagmur.bitcointicker.domain.usecase.LoginUseCase
import com.damlayagmur.bitcointicker.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : BaseViewModel() {

    private val _user = MutableLiveData<Resource<Boolean>>()
    val user: LiveData<Resource<Boolean>>
        get() = _user


    fun signInWithEmail(email: String, password: String) {
        viewModelScope.launch {
            _user.value = Resource.Loading()
            when (loginUseCase.signInWithEmail(email, password)) {
                is EmailAuthResult.UserSuccess -> {
                    _user.value = Resource.Success(true)
                }
                is EmailAuthResult.UserFailure -> {
                    _user.value = Resource.Error("Error")
                }
            }
        }
    }
}