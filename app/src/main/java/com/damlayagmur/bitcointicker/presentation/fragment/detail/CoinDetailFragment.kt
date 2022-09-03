package com.damlayagmur.bitcointicker.presentation.fragment.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.damlayagmur.bitcointicker.R
import com.damlayagmur.bitcointicker.common.Resource
import com.damlayagmur.bitcointicker.common.viewBinding
import com.damlayagmur.bitcointicker.databinding.FragmentCoinDetailBinding
import com.damlayagmur.bitcointicker.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoinDetailFragment : BaseFragment(R.layout.fragment_coin_detail) {

    private val binding by viewBinding(FragmentCoinDetailBinding::bind)

    private val coinDetailViewModel: CoinDetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        coinDetailViewModel.getCoinDetail("0-5x-long-bitcoin-token")
        observeModel()
    }

    private fun observeModel() {
        coinDetailViewModel.coinDetailLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    //binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    Log.d("TAG", "observeModel: ")

                }
                is Resource.Error -> {
                    //binding.progressBar.visibility = View.INVISIBLE
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
}