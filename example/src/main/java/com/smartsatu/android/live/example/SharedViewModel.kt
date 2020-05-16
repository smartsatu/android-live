package com.smartsatu.android.live.example

import com.smartsatu.android.live.AlternateAction
import com.smartsatu.android.live.LiveEvent
import com.smartsatu.android.live.StateDataHolder
import com.smartsatu.android.live.StateViewModel

class SharedViewModel : StateViewModel() {

    fun showToast() {
        sendEvent(Event.ShowToast("Hello from shared view model"))
        setEmptyState()
    }

    private fun setEmptyState() {
        setState(StateDataHolder(
                imageRes = R.drawable.ic_baseline_face_24,
                title = "No items",
                text = "Unfortunately items are not available now",
                alternateAction1 = AlternateAction("Show Error") {
                    setErrorState()
                },
                alternateAction2 = AlternateAction("Close") {
                    stateLiveData.value = null
                }
        ))
    }

    private fun setErrorState() {
        stateLiveData.value = StateDataHolder(
                imageRes = R.drawable.ic_baseline_error_outline_24,
                title = "Error",
                text = "Unexpected error has occurred",
                alternateAction1 = AlternateAction("Show Empty") {
                    setEmptyState()
                },
                alternateAction2 = AlternateAction("Close") {
                    setState(null)
                }
        )
    }

    sealed class Event : LiveEvent() {
        class ShowToast(val toast: String) : Event()
    }
}