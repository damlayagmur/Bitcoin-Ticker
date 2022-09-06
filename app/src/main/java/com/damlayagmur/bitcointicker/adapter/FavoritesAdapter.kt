package com.damlayagmur.bitcointicker.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.damlayagmur.bitcointicker.R
import com.damlayagmur.bitcointicker.common.loadImage
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem

class FavoritesAdapter : AbstractItem<FavoritesAdapter.ViewHolder>() {

    var id: String? = null
    var name: String? = null
    var symbol: String? = null
    var img: String? = null
    var price: String? = null

    override val layoutRes: Int
        get() = R.layout.favorite_item
    override val type: Int
        get() = com.mikepenz.fastadapter.R.id.fastadapter_item_adapter

    override fun getViewHolder(v: View): ViewHolder {
        return ViewHolder(v)
    }

    class ViewHolder(view: View) : FastAdapter.ViewHolder<FavoritesAdapter>(view) {

        var tvName: TextView = view.findViewById(R.id.textView_name)
        var tvSymbol: TextView = view.findViewById(R.id.textView_symbol)
        var ivCoin: ImageView = view.findViewById(R.id.ivCoin)
        var tvPrice: TextView = view.findViewById(R.id.textView_price)

        override fun bindView(item: FavoritesAdapter, payloads: List<Any>) {
            tvName.text = item.name
            tvSymbol.text = item.symbol
            tvPrice.text = "$ ${item.price}"
            ivCoin.loadImage(item.img)
        }

        override fun unbindView(item: FavoritesAdapter) {
            tvName.text = null
            tvSymbol.text = null
            tvPrice.text = null
        }
    }
}