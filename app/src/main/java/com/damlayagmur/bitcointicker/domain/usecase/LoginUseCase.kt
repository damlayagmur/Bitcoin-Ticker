package com.damlayagmur.bitcointicker.domain.usecase

import com.damlayagmur.bitcointicker.data.repository.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository
) {
    suspend operator fun invoke(email: String, password: String) =
        loginRepository.login(email, password)
}