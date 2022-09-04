package com.damlayagmur.bitcointicker.presentation.fragment.register

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.damlayagmur.bitcointicker.EmailAuthResult
import com.damlayagmur.bitcointicker.common.Resource
import com.damlayagmur.bitcointicker.domain.usecase.RegisterUseCase
import com.damlayagmur.bitcointicker.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : BaseViewModel() {

    private val _user = MutableLiveData<Resource<Boolean>>()
    val user: LiveData<Resource<Boolean>>
        get() = _user

    fun register(activity: Activity, email: String, password: String) {
        viewModelScope.launch {
            _user.value = Resource.Loading()
            when (registerUseCase.registerWithEmail(email, password, activity)) {
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