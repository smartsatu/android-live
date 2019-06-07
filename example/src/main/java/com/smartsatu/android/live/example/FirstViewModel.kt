package com.smartsatu.android.live.example

import android.app.Application
import com.smartsatu.android.live.LiveCallback
import com.smartsatu.android.live.LiveViewModel

class FirstViewModel(application: Application) : LiveViewModel(application) {

    fun openFragment2() {
        listener.value = Callbacks.OpenSecondFragment
    }

    sealed class Callbacks : LiveCallback() {
        object OpenSecondFragment : Callbacks()
    }
}
