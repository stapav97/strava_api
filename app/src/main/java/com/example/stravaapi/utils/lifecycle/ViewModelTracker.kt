package com.example.stravaapi.utils.lifecycle

import com.example.stravaapi.ui.base.BaseViewModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ViewModelTracker @Inject constructor() {
    enum class Event {
        Created,
        Destroyed,
    }

    interface Observer {
        fun onEvent(event: Event, id: BaseViewModel)
    }

    //==============================================================================================
    // *** Subscribers ***
    //==============================================================================================

    private val mSubscribers: MutableSet<Observer> = mutableSetOf()

    private fun emit(event: Event, id: BaseViewModel) {
        mSubscribers.forEach { it.onEvent(event, id) }
    }

    //==============================================================================================
    // *** ViewModel ***
    //==============================================================================================
    private val mViewModels: MutableSet<BaseViewModel> = mutableSetOf()

    fun onVmCreated(viewModel: BaseViewModel) {
        mViewModels.add(viewModel)
        emit(Event.Created, viewModel)
    }

    fun onVmDestroyed(viewModel: BaseViewModel) {
        mViewModels.remove(viewModel)

        emit(Event.Destroyed, viewModel)
    }

}