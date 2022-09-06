package com.damlayagmur.bitcointicker.presentation.fragment.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.damlayagmur.bitcointicker.common.Resource
import com.damlayagmur.bitcointicker.domain.usecase.LoginUseCase
import com.damlayagmur.bitcointicker.presentation.base.BaseViewModel
import com.google.firebase.auth.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : BaseViewModel() {

    private val _user = MutableLiveData<Resource<AuthResult?>>()
    val user: LiveData<Resource<AuthResult?>>
        get() = _user

    fun login(email: String, password: String) = viewModelScope.launch {
        loginUseCase.invoke(email, password).collect {
            when (it) {
                is Resource.Loading -> {
                    _user.value = Resource.Loading()
                }
                is Resource.Success -> {
                    _user.value = Resource.Success(it.data)
                }
                is Resource.Error -> {
                    _user.value = Resource.Error(it.errorMessage)
                }
            }
        }
    }
}