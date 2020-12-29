package com.example.stravaapi.data.repositories

import com.example.stravaapi.BuildConfig
import com.example.stravaapi.data.model.AccessToken
import com.example.stravaapi.data.network.StravaAuthService
import javax.inject.Inject

class NetworkRepository @Inject constructor(
    private val stravaAuthService: StravaAuthService,
) {

    fun getAccessToken(code: String): AccessToken {
        return stravaAuthService.getAccessToken(
            BuildConfig.CLIENT_ID,
            BuildConfig.CLIENT_SECRET,
            code
        )
    }
}