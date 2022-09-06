package com.damlayagmur.bitcointicker.presentation.fragment.detail

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.damlayagmur.bitcointicker.R
import com.damlayagmur.bitcointicker.common.Resource
import com.damlayagmur.bitcointicker.common.loadImage
import com.damlayagmur.bitcointicker.common.viewBinding
import com.damlayagmur.bitcointicker.data.model.detail.CoinDetailModel
import com.damlayagmur.bitcointicker.databinding.FragmentCoinDetailBinding
import com.damlayagmur.bitcointicker.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoinDetailFragment : BaseFragment(R.layout.fragment_coin_detail) {

    private val binding by viewBinding(FragmentCoinDetailBinding::bind)
    private val coinDetailViewModel: CoinDetailViewModel by viewModels()
    val args: CoinDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        coinDetailViewModel.getCoinDetail(args.coinId)

        coinDetailViewModel.checkFavorite(args.coinId)

        observeModel()

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
        coinDetailViewModel.favStatus.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                }
                is Resource.Success -> {
                    prepareFavButton(it.data)
                }
                is Resource.Error -> {
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
            var drawable: Int = R.drawable.ic_baseline_star_border_24
            var text = "Add To Favorite list"
            if (status!!) {
                text = "in the fav list"
                drawable = R.drawable.ic_baseline_star_24
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
}