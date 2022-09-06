package com.damlayagmur.bitcointicker.data.repository

import com.damlayagmur.bitcointicker.common.Resource
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) {
    fun login(
        email: String,
        password: String
    ) = flow {
        emit(Resource.Loading())
        emit(Resource.Success(firebaseAuth.signInWithEmailAndPassword(email, password).await()))
    }.catch {
        emit(Resource.Error(it.message))
    }
}