package com.damlayagmur.bitcointicker.presentation.fragment.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.damlayagmur.bitcointicker.EmailAuthResult
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

    private val _user = MutableLiveData<Resource<EmailAuthResult>>()
    val user: LiveData<Resource<EmailAuthResult>>
        get() = _user


    fun signInWithEmail(email: String, password: String) {
        viewModelScope.launch {
            when (val result = loginUseCase.signInWithEmail(email, password)) {
                is EmailAuthResult.UserSuccess -> {
                    Log.d("TAG", "signInWithEmail: ")
                }
                is EmailAuthResult.UserFailure -> {
                    Log.d("TAG", "signInWithEmail: ")
                }
            }
        }
    }
}