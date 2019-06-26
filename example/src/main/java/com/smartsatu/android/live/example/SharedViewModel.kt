package com.smartsatu.android.live.example

import android.app.Application
import com.smartsatu.android.live.LiveCallback
import com.smartsatu.android.live.LiveViewModel

class SharedViewModel(application: Application) : LiveViewModel(application) {

    fun showToast() {
        listener.value = Callback.ShowToast("Hello from shared view model")
    }

    sealed class Callback : LiveCallback() {
        class ShowToast(val toast: String) : Callback()
    }
}