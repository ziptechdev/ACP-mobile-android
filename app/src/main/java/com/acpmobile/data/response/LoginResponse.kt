package com.acpmobile.data.response

import java.util.Objects

class LoginResponse(
    val statusCode: Int,
    val message: String,
    val data: Objects? = null
)