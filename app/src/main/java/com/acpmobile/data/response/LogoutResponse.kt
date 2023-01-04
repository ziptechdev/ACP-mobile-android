package com.acpmobile.data.response

import java.util.Objects

data class LogoutResponse(
    val statusCode: Int,
    val message: String,
    val data: Objects? = null
)