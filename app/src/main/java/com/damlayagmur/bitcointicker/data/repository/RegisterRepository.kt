package com.damlayagmur.bitcointicker.data.repository

import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class RegisterRepository @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) {
    fun registerWithEmail(email: String, password: String) =
        firebaseAuth.createUserWithEmailAndPassword(email, password)
}
