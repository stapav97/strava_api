package com.example.stravaapi.ui.base

import androidx.lifecycle.ViewModel
import com.example.stravaapi.App
import com.example.stravaapi.utils.Logger
import com.example.stravaapi.utils.lifecycle.ViewModelTracker
import com.example.stravaapi.utils.logTag

open class BaseViewModel : ViewModel() {
    private val logTag = logTag()

    val mViewModelTracker: ViewModelTracker

    protected lateinit var mId: String

    init {
        Logger.d(logTag, "[VM] init hash: ${hashCode()}")
        mViewModelTracker = App.instance.component!!.provideViewModelTracker()
    }

    fun setId(id: String) {
        Logger.d(logTag, "[VM] setId $id hash: ${hashCode()}")
        mId = id
        mViewModelTracker.onVmCreated(this)
    }

    fun id() = mId

    override fun onCleared() {
        Logger.d(logTag, "[VM] onCleared hash: ${hashCode()}")
        mViewModelTracker.onVmDestroyed(this)
        super.onCleared()
    }
}