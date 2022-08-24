package com.damlayagmur.bitcointicker.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.damlayagmur.bitcointicker.R
import com.damlayagmur.bitcointicker.common.setupWithNavController
import com.damlayagmur.bitcointicker.common.viewBinding
import com.damlayagmur.bitcointicker.databinding.ActivityMainBinding
import com.damlayagmur.bitcointicker.presentation.base.BaseActivity
import com.damlayagmur.bitcointicker.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity(), BaseFragment.FragmentNavigation {

    private val binding by viewBinding(ActivityMainBinding::inflate)
    private var currentNavController: LiveData<NavController>? = null

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        }

        supportActionBar?.setDefaultDisplayHomeAsUpEnabled(false)
        //supportActionBar?.setDisplayShowHomeEnabled(false)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        setupBottomNavigationBar()
    }

    private fun setupBottomNavigationBar() {
        val navGraphIds =
            listOf(
                R.navigation.app,
                R.navigation.favorite,
                R.navigation.main_nav_graph,
                R.navigation.home
            )

        val controller = binding.bottomNav.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.nav_host_fragment,
            intent = intent
        )

        currentNavController = controller

    }

    override fun giveAction(action: Int) {
        currentNavController?.value?.navigate(action)
    }

    override fun navigateUP() {
        currentNavController?.value?.navigateUp()
    }

    override fun navigateTop() {
        finish()
        startActivity(intent)
        currentNavController?.value?.navigate(R.id.app)
    }

    override fun setBottomBarVisibility(isVisible: Boolean) {
        binding.bottomNav.visibility = if (isVisible) View.VISIBLE else View.GONE
    }
}