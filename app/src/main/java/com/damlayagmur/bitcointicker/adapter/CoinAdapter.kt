package com.damlayagmur.bitcointicker.adapter

import android.view.View
import android.widget.TextView
import com.damlayagmur.bitcointicker.R
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem

class CoinAdapter : AbstractItem<CoinAdapter.ViewHolder>() {

    var id: String? = null
    var name: String? = null
    var symbol: String? = null

    override val layoutRes: Int
        get() = R.layout.coin_list_item
    override val type: Int
        get() = com.mikepenz.fastadapter.R.id.fastadapter_item_adapter

    override fun getViewHolder(v: View): ViewHolder {
        return ViewHolder(v)
    }

    class ViewHolder(view: View) : FastAdapter.ViewHolder<CoinAdapter>(view) {

        var tvName: TextView = view.findViewById(R.id.textView_name)
        var tvSymbol: TextView = view.findViewById(R.id.textView_symbol)

        override fun bindView(item: CoinAdapter, payloads: List<Any>) {
            tvName.text = item.name
            tvSymbol.text = item.symbol
        }

        override fun unbindView(item: CoinAdapter) {
            tvName.text = null
            tvSymbol.text = null
        }
    }
}