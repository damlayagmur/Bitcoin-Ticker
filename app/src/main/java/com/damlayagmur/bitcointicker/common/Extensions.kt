package com.damlayagmur.bitcointicker.common

import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import coil.api.load
import com.damlayagmur.bitcointicker.R

fun Fragment.navigate(navDirections: NavDirections) = findNavController().navigate(navDirections)

/**
 * Load Image with Coil
 */
fun ImageView.loadImage(url: String?) {
    if (url != null) {
        this.load(url) {
            placeholder(R.drawable.ic_launcher_background)
            error(R.drawable.ic_launcher_background)
            crossfade(false)
        }
    }
}
