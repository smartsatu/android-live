package com.smartsatu.android.live.example

import com.smartsatu.android.live.LiveEvent
import com.smartsatu.android.live.StateViewModel

class FirstViewModel : StateViewModel() {


    fun openFragment2() {
        sendEvent(Event.OpenSecondFragment)
    }

    sealed class Event : LiveEvent() {
        object OpenSecondFragment : Event()
    }
}
