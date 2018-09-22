package com.smartsatu.android.live

enum class Status {
    RUNNING,
    SUCCESS,
    EMPTY,
    FAILED
}

@Suppress("DataClassPrivateConstructor")
data class NetworkState private constructor(
        val status: Status,
        val message: String? = null,
        val throwable: Throwable? = null) {
    companion object {
        val LOADED = NetworkState(Status.SUCCESS)
        val EMPTY = NetworkState(Status.EMPTY)
        val LOADING = NetworkState(Status.RUNNING)
        val ERROR = NetworkState(Status.FAILED)
        fun error(msg: String?, throwable: Throwable) = NetworkState(Status.FAILED, msg, throwable)
    }
}

