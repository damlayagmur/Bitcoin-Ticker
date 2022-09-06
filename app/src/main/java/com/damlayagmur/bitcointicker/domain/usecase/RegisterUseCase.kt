package com.damlayagmur.bitcointicker.domain.usecase

import com.damlayagmur.bitcointicker.data.repository.RegisterRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val registerRepository: RegisterRepository
) {
    suspend operator fun invoke(email: String, password: String) =
        registerRepository.register(email, password)
}