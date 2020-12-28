package com.example.stravaapi.ui.login

import com.example.stravaapi.data.repositories.NetworkRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginActivityViewModel @Inject constructor(
    private val mNetworkRepository: NetworkRepository,
){
}