package com.acpmobile.data.request

import okhttp3.MultipartBody
import java.io.Serializable

data class UserVerificationRequest(
    var documentIdFront: MultipartBody.Part? = null,
    var documentIdBack: MultipartBody.Part? = null,
    var selfie: MultipartBody.Part? = null,
    var username: String? = "milantukic198923@gmail.com",
    var userIp: String? = "192.168.0.1",
    var userState: String? = "IL",
    var consentOptained: String? =  "yes",
    var consentOptainedAt: String? =  "2023-01-05T17:20:35.000Z"

    ) : Serializable