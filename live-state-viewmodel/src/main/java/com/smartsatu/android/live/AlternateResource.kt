package com.smartsatu.android.live

import android.net.Uri

sealed class AlternateResource {
    class UriResource(val uri: Uri) : AlternateResource()
}
