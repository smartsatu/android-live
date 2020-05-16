package com.smartsatu.android.live

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.smartsatu.android.live.internal.CallbackLiveData

open class StateViewModel : ViewModel() {

    val listener: LiveData<LiveEvent> = CallbackLiveData<LiveEvent>()
    protected val stateLiveData = MutableLiveData<StateDataHolder?>()
    val state = Transformations.map(stateLiveData) { it }

    fun sendEvent(event: LiveEvent) {
        (listener as CallbackLiveData).value = event
    }

    fun setState(state: StateDataHolder?) {
        stateLiveData.value = state
    }
}