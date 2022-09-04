package com.damlayagmur.bitcointicker.presentation.fragment.login

import android.os.Bundle
import android.view.View
import com.damlayagmur.bitcointicker.R
import com.damlayagmur.bitcointicker.common.navigate
import com.damlayagmur.bitcointicker.common.viewBinding
import com.damlayagmur.bitcointicker.databinding.FragmentLoginBinding
import com.damlayagmur.bitcointicker.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment(R.layout.fragment_login) {

    private val binding by viewBinding(FragmentLoginBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btn.setOnClickListener {
            navigate(LoginFragmentDirections.actionLoginFragmentToHome())
        }
        binding.btnRegisterLogin.setOnClickListener {
            navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
        }
    }
}