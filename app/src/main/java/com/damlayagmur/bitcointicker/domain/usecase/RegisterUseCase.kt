package com.damlayagmur.bitcointicker.domain.usecase

import android.app.Activity
import com.damlayagmur.bitcointicker.EmailAuthResult
import com.damlayagmur.bitcointicker.data.repository.RegisterRepository
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class RegisterUseCase @Inject constructor(
    private val registerRepository: RegisterRepository
) {
    suspend fun registerWithEmail(
        email: String,
        password: String,
        activity: Activity
    ): EmailAuthResult =
        suspendCoroutine { continuation ->
            registerRepository.registerWithEmail(email, password)
                .addOnCompleteListener(activity) { task ->
                    if (task.isSuccessful) {
                        val user = task.result?.user
                        user?.let {
                            continuation.resume(EmailAuthResult.UserSuccess(user))
                        }
                    } else
                        task.exception?.cause?.let {
                            continuation.resumeWithException(it)
                        }
                }
        }
}