package com.damlayagmur.bitcointicker.common

import com.google.firebase.auth.FirebaseUser

sealed class EmailAuthResult {
    data class UserSuccess(val credential: FirebaseUser) : EmailAuthResult()
    data class UserFailure(val errorMessage: String?) : EmailAuthResult()
}