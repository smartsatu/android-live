package com.smartsatu.android.live

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.smartsatu.android.live.internal.CallbackLiveData

class StateAndroidViewModel(application: Application) : AndroidViewModel(application) {

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