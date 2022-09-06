package com.damlayagmur.bitcointicker.presentation.fragment.splash

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.damlayagmur.bitcointicker.R
import com.damlayagmur.bitcointicker.common.navigate
import com.damlayagmur.bitcointicker.presentation.base.BaseFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : BaseFragment(R.layout.fragment_splash) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mFragmentNavigation.setBottomBarVisibility(false)

        lifecycleScope.launch {
            delay(1000)
            navigate(SplashFragmentDirections.actionSplashFragmentToLoginFragment())
        }
    }
}