package com.example.stravaapi.utils

import android.app.Activity
import android.content.Context
import android.os.IBinder
import android.view.View
import android.view.inputmethod.InputMethodManager

fun hideKeyboard(activity: Activity, windowToken: IBinder) {
    val imn = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imn.hideSoftInputFromWindow(windowToken, 0)
}

fun View.gone(): View {
    if (visibility == View.GONE) return this

    visibility = View.GONE
    return this
}

fun View.show(): View {
    if (visibility == View.VISIBLE) return this

    visibility = View.VISIBLE
    return this
}
