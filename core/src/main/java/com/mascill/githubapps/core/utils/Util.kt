package com.mascill.githubapps.core.utils

import android.content.Intent
import android.os.Build
import android.os.Parcelable
import android.view.View

fun showLoading(loadingView: View) {
    loadingView.visibility = View.VISIBLE
}

fun hideLoading(loadingView: View) {
    loadingView.visibility = View.GONE
}

fun convertToK(number: Int): String {
    return when {
        number < 1000 -> number.toString()
        number < 10000 -> "${number / 1000}.${(number % 1000) / 100}K"
        else -> "${number / 1000}.${(number % 1000) / 100}K"
    }
}

inline fun <reified T : Parcelable> Intent.parcelable(key: String): T? = when {
    Build.VERSION.SDK_INT >= 33 -> getParcelableExtra(key, T::class.java)
    else -> @Suppress("DEPRECATION") getParcelableExtra(key) as? T
}
