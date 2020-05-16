package com.smartsatu.android.live.internal

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.smartsatu.android.live.LiveEvent


/**
 * Modified copy of java implementation from google samples:
 * https://github.com/googlesamples/android-architecture/blob/dev-todo-mvvm-live/todoapp/app/src/main/java/com/example/android/architecture/blueprints/todoapp/SingleLiveEvent.java
 * Completely different solution based on LiveData implementation
 * https://www.reddit.com/r/androiddev/comments/9cc3en/why_use_singleliveevent_livedata_at_all/
 *
 * A lifecycle-aware observable that sends only new updates after subscription, used for events like
 * navigation and Snackbar messages.
 * <p>
 * This avoids a common problem with events: on configuration change (like rotation) an update
 * can be emitted if the observer is active. This LiveData only calls the observable if there's an
 * explicit call to setValue() or call().
 * <p>
 * Note that only one observer is going to be notified of changes.
 *
 * TODO: None of the above is right. The class was completely rewritten to respect all the issues with
 * callback behavior:
 * 1. Single event
 * 2. No events when subscribed after instance changes and so on (not emitted last known event)
 * 3. All subscribers must be observed
 *
 * Latest issue: propagation condition must be implemented
 */
internal class CallbackLiveData<T : LiveEvent> : androidx.lifecycle.MutableLiveData<T>() {

    private val internalMutableLiveData = MutableLiveData<T>()

    override fun setValue(value: T?) {
        internalMutableLiveData.value = value
    }

    override fun postValue(value: T?) {
        internalMutableLiveData.postValue(value)
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        internalMutableLiveData.observe(owner, observer)
    }

    override fun onInactive() {
        internalMutableLiveData.onInactive()
    }

    override fun hasObservers(): Boolean {
        return internalMutableLiveData.hasObservers()
    }

    override fun removeObservers(owner: LifecycleOwner) {
        internalMutableLiveData.removeObservers(owner)
    }

    override fun observeForever(observer: Observer<in T>) {
        internalMutableLiveData.observeForever(observer)
    }

    override fun removeObserver(observer: Observer<in T>) {
        internalMutableLiveData.removeObserver(observer)
    }

    override fun getValue(): T? {
        return internalMutableLiveData.value
    }

    override fun hasActiveObservers(): Boolean {
        return internalMutableLiveData.hasActiveObservers()
    }

    override fun onActive() {
        internalMutableLiveData.onActive()
    }
}