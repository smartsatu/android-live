package com.smartsatu.android.live

import com.smartsatu.android.live.LiveStateType.*

data class LiveState(val type: LiveStateType = DEFAULT,
                     val imageRes: Int? = null,
                     val title: String = "",
                     val text: String = "",
                     val button: String = "") {

    fun isVisible(): Boolean {
        return title.isNotEmpty() || text.isNotEmpty() || button.isNotEmpty()
    }
}

fun NetworkState.toLiveState(
        emptyState: LiveState = LiveState(EMPTY),
        errorState: LiveState = LiveState(ERROR),
        loadingState: LiveState = LiveState(LOADING),
        successState: LiveState = LiveState(SUCCESS),
        defaultState: LiveState = LiveState(DEFAULT)): LiveState? = when (this) {
    NetworkState.EMPTY -> emptyState
    NetworkState.ERROR -> errorState
    NetworkState.LOADING -> loadingState
    NetworkState.LOADED -> successState
    else -> defaultState
}
