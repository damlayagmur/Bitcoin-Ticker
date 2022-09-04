package com.damlayagmur.bitcointicker.presentation.fragment.register

import android.app.Activity
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.damlayagmur.bitcointicker.EmailAuthResult
import com.damlayagmur.bitcointicker.domain.usecase.RegisterUseCase
import com.damlayagmur.bitcointicker.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : BaseViewModel() {

    fun register(activity: Activity, email: String, password: String) {
        viewModelScope.launch {
            when (val result = registerUseCase.registerWithEmail(email, password, activity)) {
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