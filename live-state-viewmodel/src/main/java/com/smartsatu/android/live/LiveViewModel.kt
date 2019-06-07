package com.smartsatu.android.live

import android.app.Application
import androidx.annotation.StringRes
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import com.smartsatu.android.core.content.isPermissionsRequired

/**
 * Created by sayplz
 * on 1/19/18.
 */

abstract class LiveViewModel(application: Application) : AndroidViewModel(application), LifecycleObserver {
    val isRefreshing = MutableLiveData<Boolean>()
    val listener: MutableLiveData<LiveCallback> = CallbackLiveData()
    val liveState = MutableLiveData<LiveState>().apply { createDefaultLiveState() }

    open fun onButtonStateClicked() {
        setError(null)
    }

    protected fun setError(@StringRes resId: Int) {
        setError(string(resId))
    }

    protected fun setError(text: String?) {
        liveState.value = liveState.value?.copy(title = text ?: "") ?: createDefaultLiveState()
    }

    private fun createDefaultLiveState(): LiveState {
        val stateButtonTitle = getContext().getString(R.string.retry)
        return LiveState(button = stateButtonTitle)
    }

    protected fun getContext() = getApplication<Application>().applicationContext

    protected fun string(@StringRes stringRes: Int): String = getContext().getString(stringRes)

    protected fun checkRequiredPermissions(vararg permissions: String) = permissions.filter { getContext().isPermissionsRequired(it) }
}
