package com.acpmobile.data.response

import java.util.Objects

class LogoutResponse(
    val statusCode: Int,
    val message: String,
    val data: Objects? = null
)