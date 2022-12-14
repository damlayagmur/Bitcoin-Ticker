package com.damlayagmur.bitcointicker.presentation.fragment.register

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.damlayagmur.bitcointicker.R
import com.damlayagmur.bitcointicker.common.Resource
import com.damlayagmur.bitcointicker.common.showToast
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

        initComponents()
        observeModel()
    }

    private fun initComponents() {
        binding.btnRegister.setOnClickListener {
            if (binding.registerMail.text.isEmpty() || binding.registerPassword.text.isEmpty()) {
                requireContext().showToast(getString(R.string.checkLogin))
            } else {
                viewModel.register(
                    binding.registerMail.text.toString(),
                    binding.registerPassword.text.toString()
                )
            }
        }
    }

    private fun observeModel() {
        viewModel.user.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    requireActivity().onBackPressed()
                }
                is Resource.Error -> {
                    binding.progressBar.visibility = View.INVISIBLE
                    requireContext().showToast(it.errorMessage)
                }
            }
        }
    }
}