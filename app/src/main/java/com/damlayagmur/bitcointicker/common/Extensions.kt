package com.damlayagmur.bitcointicker.common

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import coil.api.load
import com.blankj.utilcode.util.StringUtils
import com.damlayagmur.bitcointicker.R

fun Fragment.navigate(navDirections: NavDirections) = findNavController().navigate(navDirections)

/**
 * Load Image with Coil
 */
fun ImageView.loadImage(url: String?) {
    if (url != null) {
        this.load(url) {
            placeholder(null)
            error(R.drawable.ic_error)
            crossfade(false)
        }
    }
}

fun Context.showToast(message: String?) {
    Toast.makeText(
        this,
        message ?: StringUtils.getString(R.string.default_error_message),
        Toast.LENGTH_LONG
    ).show()
}
