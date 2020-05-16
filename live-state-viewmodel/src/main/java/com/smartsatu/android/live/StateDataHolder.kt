package com.smartsatu.android.live

data class StateDataHolder(
        val imageRes: Int? = null,
        val title: String = "",
        val text: String = "",
        val button: String = "",
        val alternateAction1: AlternateAction? = null,
        val alternateAction2: AlternateAction? = null
)