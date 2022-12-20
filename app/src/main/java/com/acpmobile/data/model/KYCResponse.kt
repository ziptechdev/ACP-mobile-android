package com.acpmobile.data.model

data class KYCResponse(
    val statusCode: Int,
    val message: String,
    val data: User
)