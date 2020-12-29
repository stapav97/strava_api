package com.example.stravaapi.ui.login

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.stravaapi.data.repositories.NetworkRepository
import com.example.stravaapi.ui.base.BaseViewModel
import com.example.stravaapi.ui.base.commands.ViewCommandProcessor
import com.example.stravaapi.ui.base.commands.enqueue
import com.example.stravaapi.utils.Reporter
import com.example.stravaapi.utils.logTag
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginViewCommandProcessor @Inject constructor() : ViewCommandProcessor<LoginActivity>()

@Singleton
class LoginActivityViewModel @Inject constructor(
    private val mNetworkRepository: NetworkRepository,
    private val mCommands: LoginViewCommandProcessor,
) : BaseViewModel() {

    private val logTag = logTag()

    data class State(
        val isProgress: Boolean = false,
        val token: String = "",
        val errorMessage: String = "",
    )

    private val mState: MutableLiveData<State>
    fun state(): LiveData<State> = mState


    fun observeCommands(owner: LifecycleOwner, view: LoginActivity) {
        mCommands.observe(owner, view)
    }

    init {
        setId(logTag)
        mState = MutableLiveData(State())
    }

}