package com.example.stravaapi

import android.app.Application
import com.example.stravaapi.di.AppComponent
import com.example.stravaapi.di.DaggerAppComponent
import com.example.stravaapi.utils.lifecycle.addLifecycleLogger
import com.example.stravaapi.utils.network.NetworkMonitor

class App : Application() {

    var component: AppComponent? = null
        private set

    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
        component = DaggerAppComponent.builder().also {
            it.context(this)
        }.build()

        NetworkMonitor(this).startNetworkCallBack()

        addLifecycleLogger()
    }


    override fun onTerminate() {
        super.onTerminate()
        NetworkMonitor(this).stopNetworkCallback()
    }
}