package com.smartsatu.android.live

typealias ActionListener = (action: AlternateAction) -> Unit

class AlternateAction(val title: String? = null, internal val listener: ActionListener) {

    fun notifyListener() {
        listener.invoke(this)
    }
}
