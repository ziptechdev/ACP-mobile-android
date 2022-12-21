package com.acpmobile.data.response

import com.acpmobile.data.model.User

data class KYCResponse(
    val statusCode: Int,
    val message: String,
    val data: User
)