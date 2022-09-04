package com.damlayagmur.bitcointicker.domain.usecase

import com.damlayagmur.bitcointicker.common.EmailAuthResult
import com.damlayagmur.bitcointicker.data.repository.LoginRepository
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository
) {
    suspend fun signInWithEmail(email: String, password: String): EmailAuthResult =
        suspendCoroutine { continuation ->
            loginRepository.signInWithEmail(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = task.result?.user
                    user?.let {
                        continuation.resume(EmailAuthResult.UserSuccess(it))
                    }
                } else {
                    task.exception?.cause?.let {
                        continuation.resumeWithException(it)
                    }
                }
            }
        }
}