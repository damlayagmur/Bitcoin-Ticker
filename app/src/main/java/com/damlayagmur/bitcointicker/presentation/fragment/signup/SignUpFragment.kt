package com.damlayagmur.bitcointicker.presentation.fragment.signup

import com.damlayagmur.bitcointicker.R
import com.damlayagmur.bitcointicker.common.viewBinding
import com.damlayagmur.bitcointicker.databinding.FragmentSignupBinding
import com.damlayagmur.bitcointicker.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : BaseFragment(R.layout.fragment_signup) {

    private val binding by viewBinding(FragmentSignupBinding::bind)

}