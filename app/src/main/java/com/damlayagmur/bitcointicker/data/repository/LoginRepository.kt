package com.damlayagmur.bitcointicker.data.repository

import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) {
    fun signInWithEmail(email: String, password: String) =
        firebaseAuth.signInWithEmailAndPassword(email, password)
}