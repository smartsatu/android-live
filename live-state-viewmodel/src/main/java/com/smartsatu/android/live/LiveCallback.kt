package com.smartsatu.android.live

/**
 * Created by sayplz
 * on 1/30/18.
 */
open class LiveCallback {

    open class AuthorizationRequested : LiveCallback()

    open class RequestPermissions(val permissions: Array<String>) : LiveCallback()
}