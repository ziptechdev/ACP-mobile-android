package com.acpmobile.data.request

import java.io.Serializable

data class EligibilityRegisterRequest(
    val username: String?,
    val password: String?
    ) : Serializable