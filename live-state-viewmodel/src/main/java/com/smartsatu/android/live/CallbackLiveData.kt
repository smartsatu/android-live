package com.smartsatu.android.live

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

internal class CallbackLiveData<T : LiveCallback?> : MutableLiveData<T>() {

    private val mPending = AtomicBoolean(false)

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        super.observe(owner, Observer { callback ->
            if (mPending.compareAndSet(true, false)) {
                observer.onChanged(callback)
            }
        })
    }

    @MainThread
    override fun setValue(callback: T?) {
        mPending.set(true)
        super.setValue(callback)
    }
}