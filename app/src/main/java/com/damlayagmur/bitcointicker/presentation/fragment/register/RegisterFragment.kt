package com.damlayagmur.bitcointicker.presentation.fragment.register

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.damlayagmur.bitcointicker.R
import com.damlayagmur.bitcointicker.common.Resource
import com.damlayagmur.bitcointicker.common.viewBinding
import com.damlayagmur.bitcointicker.databinding.FragmentRegisterBinding
import com.damlayagmur.bitcointicker.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragment(R.layout.fragment_register) {

    private val binding by viewBinding(FragmentRegisterBinding::bind)
    private val viewModel: RegisterViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRegister.setOnClickListener {
            viewModel.register(
                requireActivity(),
                binding.mail.text.toString(),
                binding.password.text.toString()
            )
        }
        observeModel()
    }

    private fun observeModel() {
        viewModel.user.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                }
                is Resource.Success -> {
                    requireActivity().onBackPressed()
                }
                is Resource.Error -> {

                }
            }
        }
    }
}