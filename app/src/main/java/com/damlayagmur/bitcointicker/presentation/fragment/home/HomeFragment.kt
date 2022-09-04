package com.damlayagmur.bitcointicker.presentation.fragment.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.damlayagmur.bitcointicker.R
import com.damlayagmur.bitcointicker.adapter.CoinAdapter
import com.damlayagmur.bitcointicker.common.Resource
import com.damlayagmur.bitcointicker.common.navigate
import com.damlayagmur.bitcointicker.common.viewBinding
import com.damlayagmur.bitcointicker.data.model.CoinItem
import com.damlayagmur.bitcointicker.databinding.FragmentHomeBinding
import com.damlayagmur.bitcointicker.presentation.base.BaseFragment
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.FastItemAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)

    private val coinViewModel: CoinViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mFragmentNavigation.setBottomBarVisibility(true)
        initComponents()
        observeModel()
    }

    private fun initComponents() {
        binding.etSearch.doAfterTextChanged { coinViewModel.searchCoin(it.toString()) }
        binding.btnSearch.setOnClickListener {
            navigate(HomeFragmentDirections.actionHomeFragmentToCoinDetailFragment())
        }
    }

    private fun observeModel() {
        coinViewModel.coinListLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.INVISIBLE
                    prepareList(it.data!!)

                }
                is Resource.Error -> {
                    binding.progressBar.visibility = View.INVISIBLE
                    it.errorMessage?.let { message ->
                        Toast.makeText(
                            context,
                            message,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }

    private fun prepareList(coins: List<CoinItem?>) {
        val fastConnectionsAdapter: FastItemAdapter<CoinAdapter> = FastItemAdapter()
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = FastAdapter.with(fastConnectionsAdapter)
        binding.recyclerView.itemAnimator = DefaultItemAnimator()

        for (item in coins) {
            val coin = CoinAdapter()
            coin.id = item?.id ?: ""
            coin.name = item?.name ?: ""
            coin.desc = item?.symbol ?: ""
            fastConnectionsAdapter.add(coin)
        }
    }
}