package com.example.stravaapi.data.repositories

import com.example.stravaapi.BuildConfig
import com.example.stravaapi.data.network.StravaAuthService

class NetworkRepository constructor(
    private val stravaAuthService: StravaAuthService
) {

    fun getAccessToken(code: String) {
        return stravaAuthService.getAccessToken(
            BuildConfig.CLIENT_ID,
            BuildConfig.CLIENT_SECRET,
            code
        )
    }
}