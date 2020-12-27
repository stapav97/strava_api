package com.example.stravaapi.utils

import android.util.Log
import com.example.stravaapi.BuildConfig

object Logger {
    private const val TAG = "kotlinsample.mylog"

    fun d(tag: String, message: String) {
        if (!BuildConfig.DEBUG) return

        Log.d(TAG, "$tag $message")
    }
}