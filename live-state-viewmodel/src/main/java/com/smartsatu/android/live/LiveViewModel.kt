package com.smartsatu.android.live

import android.app.Application
import android.content.Context
import androidx.annotation.StringRes
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.smartsatu.android.core.content.isPermissionsRequired
import com.smartsatu.android.live.internal.CallbackLiveData

/**
 * Created by sayplz
 * on 1/19/18.
 */

abstract class LiveViewModel(application: Application) : AndroidViewModel(application), LifecycleObserver {
    val isRefreshing = MutableLiveData<Boolean>()
    val listener: MutableLiveData<LiveCallback> = CallbackLiveData()
    val liveState = MutableLiveData<LiveState>().apply { value = LiveState() }

    /**
     * Refresh content
     */
    open fun refresh() {
        // TODO: Make it abstract. For compatibility purpose it's not now
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        if (liveState.value?.type == LiveStateType.FORBIDDEN
                || liveState.value?.type == LiveStateType.ERROR
                || liveState.value?.type == LiveStateType.EMPTY) {
            refresh()
        }
    }

    /**
     * This method should never be overwritten.
     * Just use this method to propagate click from UI to view model in order to handle current [LiveState] properly
     */
    open fun onButton1StateClicked() {
        val alternateResource = liveState.value?.alternateResource1
        internalOnButtonStateClicked(alternateResource)
    }

    /**
     * This method should never be overwritten.
     * Just use this method to propagate click from UI to view model in order to handle current [LiveState] properly
     */
    open fun onButton2StateClicked() {
        val alternateResource = liveState.value?.alternateResource2
        internalOnButtonStateClicked(alternateResource)
    }

    private fun internalOnButtonStateClicked(alternateResource: AlternateResource? = null) {
        if (alternateResource != null) {
            listener.value = LiveCallback.AlternateResourceRequested(alternateResource)
        } else {
            refresh()
        }
        liveState.value = LiveState()
    }

    protected fun getContext(): Context = getApplication<Application>().applicationContext

    protected fun string(@StringRes stringRes: Int): String = getContext().getString(stringRes)

    protected fun checkRequiredPermissions(vararg permissions: String) = permissions.filter { getContext().isPermissionsRequired(it) }
}
