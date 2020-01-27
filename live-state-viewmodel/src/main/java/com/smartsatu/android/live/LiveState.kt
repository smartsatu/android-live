package com.smartsatu.android.live

import com.smartsatu.android.live.LiveStateType.DEFAULT
import com.smartsatu.android.live.LiveStateType.EMPTY
import com.smartsatu.android.live.LiveStateType.ERROR
import com.smartsatu.android.live.LiveStateType.LOADING
import com.smartsatu.android.live.LiveStateType.SUCCESS

data class LiveState(
        val type: LiveStateType = DEFAULT,
        val imageRes: Int? = null,
        val title: String = "",
        val text: String = "",
        val button: String = "",
        val alternateResource1: AlternateResource? = null,
        val alternateResource2: AlternateResource? = null
) {

    fun isVisible(): Boolean {
        return imageRes?.let { it > 0 } == true || title.isNotEmpty() || text.isNotEmpty() || button.isNotEmpty()
    }
}

//TODO: Provide default/init values to Live states
fun NetworkState.toLiveState(
        emptyState: LiveState = LiveState(EMPTY),
        errorState: LiveState = LiveState(ERROR),
        loadingState: LiveState = LiveState(LOADING),
        successState: LiveState = LiveState(SUCCESS)): LiveState = when (status) {
    Status.EMPTY -> emptyState
    Status.FAILED -> errorState
    Status.RUNNING -> loadingState
    Status.SUCCESS -> successState
}
