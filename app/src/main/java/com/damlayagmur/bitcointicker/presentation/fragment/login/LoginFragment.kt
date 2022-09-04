package com.damlayagmur.bitcointicker.presentation.fragment.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.damlayagmur.bitcointicker.R
import com.damlayagmur.bitcointicker.common.Resource
import com.damlayagmur.bitcointicker.common.navigate
import com.damlayagmur.bitcointicker.common.viewBinding
import com.damlayagmur.bitcointicker.databinding.FragmentLoginBinding
import com.damlayagmur.bitcointicker.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment(R.layout.fragment_login) {

    private val binding by viewBinding(FragmentLoginBinding::bind)

    private val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btn.setOnClickListener {
            navigate(LoginFragmentDirections.actionLoginFragmentToHome())
        }
        binding.btnLogin.setOnClickListener {
            viewModel.signInWithEmail(
                binding.lmail.text.toString(),
                binding.lpassword.text.toString()
            )
        }
        binding.btnRegister.setOnClickListener {
            navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
        }
        observeModel()
    }

    private fun observeModel() {
        viewModel.user.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    navigate(LoginFragmentDirections.actionLoginFragmentToHome())
                }
                is Resource.Error -> {

                }
            }
        }
    }
}