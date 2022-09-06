package com.damlayagmur.bitcointicker.presentation.fragment.login

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import androidx.fragment.app.viewModels
import com.damlayagmur.bitcointicker.R
import com.damlayagmur.bitcointicker.common.Resource
import com.damlayagmur.bitcointicker.common.navigate
import com.damlayagmur.bitcointicker.common.showToast
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

        initComponents()
        observeModel()
    }

    private fun initComponents() {
        binding.btnLogin.setOnClickListener {
            // TODO: Damlaa null check + show error msg plz
            viewModel.login(
                binding.lmail.text.toString(),
                binding.lpassword.text.toString()
            )
        }

        binding.btnRegister.setOnClickListener {
            navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
        }

        val registerSpannable = SpannableString("Do not have an account? Register")
        val clickableSpannable: ClickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
            }
        }
        registerSpannable.setSpan(clickableSpannable, 24, 32, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.textView.text = registerSpannable
        binding.textView.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun observeModel() {
        viewModel.user.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    navigate(LoginFragmentDirections.actionLoginFragmentToHome())
                }
                is Resource.Error -> {
                    binding.progressBar.visibility = View.INVISIBLE
                    requireContext().showToast(it.errorMessage)
                }
            }
        }
    }
}