package com.damlayagmur.bitcointicker.presentation.fragment.favorite

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.damlayagmur.bitcointicker.R
import com.damlayagmur.bitcointicker.common.Resource
import com.damlayagmur.bitcointicker.common.navigate
import com.damlayagmur.bitcointicker.common.viewBinding
import com.damlayagmur.bitcointicker.data.model.FavoriteCoin
import com.damlayagmur.bitcointicker.databinding.FragmentFavoriteBinding
import com.damlayagmur.bitcointicker.presentation.adapter.FavoritesAdapter
import com.damlayagmur.bitcointicker.presentation.base.BaseFragment
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.FastItemAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : BaseFragment(R.layout.fragment_favorite) {

    private val binding by viewBinding(FragmentFavoriteBinding::bind)
    private val viewModel: FavoriteViewModel by viewModels()

    //region vars
    private val favoritesAdapter: FastItemAdapter<FavoritesAdapter> = FastItemAdapter()
    private val fastAdapter = FastAdapter.with(favoritesAdapter)
    //endregion

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponents()
        observeModel()
    }

    private fun initComponents() {
        mFragmentNavigation.setBottomBarVisibility(true)
        val layoutManager = LinearLayoutManager(context)

        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = fastAdapter
        binding.recyclerView.itemAnimator = DefaultItemAnimator()

        binding.swipeRefresh.setOnRefreshListener {
            binding.swipeRefresh.isRefreshing = false
            viewModel.getFavorites()
        }
    }

    private fun observeModel() {
        viewModel.favCoins.observe(viewLifecycleOwner) {
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
                }
            }
        }
    }

    private fun prepareList(coins: List<FavoriteCoin?>) {
        favoritesAdapter.clear()

        for (item in coins) {
            val coin = FavoritesAdapter()
            coin.id = item?.coinId ?: ""
            coin.name = item?.name ?: ""
            coin.symbol = item?.symbol ?: ""
            coin.price = item?.currentPrice.toString()
            coin.img = item?.img ?: ""
            favoritesAdapter.add(coin)
        }
        fastAdapter.onClickListener = { _, _, item, _ ->
            item.id?.let {
                navigate(FavoriteFragmentDirections.actionFavoriteFragmentToCoinDetailFragment(it))
            }
            false
        }
    }
}

