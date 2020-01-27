package com.smartsatu.android.live

sealed class AlternateResource {
    class Uri(val uri: android.net.Uri) : AlternateResource()
}
