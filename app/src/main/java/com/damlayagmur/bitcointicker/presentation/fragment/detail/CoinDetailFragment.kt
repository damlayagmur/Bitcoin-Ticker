package com.damlayagmur.bitcointicker.presentation.fragment.detail

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.blankj.utilcode.util.StringUtils
import com.damlayagmur.bitcointicker.R
import com.damlayagmur.bitcointicker.common.Resource
import com.damlayagmur.bitcointicker.common.loadImage
import com.damlayagmur.bitcointicker.common.showToast
import com.damlayagmur.bitcointicker.common.viewBinding
import com.damlayagmur.bitcointicker.data.model.detail.CoinDetailModel
import com.damlayagmur.bitcointicker.databinding.FragmentCoinDetailBinding
import com.damlayagmur.bitcointicker.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CoinDetailFragment : BaseFragment(R.layout.fragment_coin_detail) {

    private val binding by viewBinding(FragmentCoinDetailBinding::bind)
    private val coinDetailViewModel: CoinDetailViewModel by viewModels()
    val args: CoinDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        coinDetailViewModel.getCoinDetail(args.coinId)

        coinDetailViewModel.checkFavorite(args.coinId)

        initComponents()
        observeModel()
    }

    private fun initComponents() {
        mFragmentNavigation.setBottomBarVisibility(false)

        binding.ivRefresh.setOnClickListener {
            val refreshTime = binding.etRefresh.text.toString()
            if (refreshTime.isNotEmpty()) {
                getCoinPrice(refreshTime.toInt())
                binding.ivRefresh.isEnabled = false
                binding.etRefresh.isEnabled = false
            }
        }
        binding.btnFav.setOnClickListener {
            coinDetailViewModel.favoriteButtonClick()
        }
    }

    private fun observeModel() {
        coinDetailViewModel.coinDetailLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.INVISIBLE
                    prepareComponents(it.data)
                }
                is Resource.Error -> {
                    binding.progressBar.visibility = View.INVISIBLE
                    requireContext().showToast(it.errorMessage)
                }
            }
        }
        coinDetailViewModel.coinPrice.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.INVISIBLE
                    binding.tvCurrentPrice.text = it.data?.current_price?.usd.toString()
                }
                is Resource.Error -> {
                    binding.progressBar.visibility = View.INVISIBLE
                    requireContext().showToast(it.errorMessage)
                }
            }
        }
        coinDetailViewModel.favStatus.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.INVISIBLE
                    prepareFavButton(it.data)
                }
                is Resource.Error -> {
                    binding.progressBar.visibility = View.INVISIBLE
                    requireContext().showToast(it.errorMessage)
                }
            }
        }
    }

    private fun prepareComponents(data: CoinDetailModel?) {
        binding.ivCoin.loadImage(data?.image?.large)
        binding.tvName.text = data?.name
        binding.tvSymbol.text = data?.symbol
        binding.tvAlgorithm.text = data?.hashing_algorithm ?: "N/A"
        binding.tvCurrentPrice.text =
            data?.market_data?.current_price?.usd.toString()
        binding.tvPercentage.text =
            data?.market_data?.price_change_percentage_24h.toString()

        if (data?.market_data?.price_change_percentage_24h.toString().contains("-"))
            binding.tvPercentage.setTextColor(Color.RED)
        else
            binding.tvPercentage.setTextColor(Color.GREEN)

        binding.tvDescription.text = data?.description?.en

    }

    private fun prepareFavButton(status: Boolean?) {
        status.let {
            var drawable: Int = R.drawable.ic_star_border
            var text = StringUtils.getString(R.string.add_to_fav)
            if (status!!) {
                text = StringUtils.getString(R.string.delete_from_fav)
                drawable = R.drawable.ic_baseline_star
            }
            binding.btnFav.setCompoundDrawablesWithIntrinsicBounds(
                ContextCompat.getDrawable(
                    requireContext(),
                    drawable
                ), null, null, null
            )
            binding.btnFav.text = text
        }
    }

    private fun getCoinPrice(refreshTime: Int) {
        lifecycleScope.launch(Dispatchers.IO) {
            while (true) {
                coinDetailViewModel.getPrice(args.coinId)
                delay(refreshTime.toLong() * 1000)
            }
        }
    }
}