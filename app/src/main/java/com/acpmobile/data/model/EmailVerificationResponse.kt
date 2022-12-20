package com.acpmobile.data.model

data class EmailVerificationResponse(
    val statusCode: Int,
    val message: String,
    val data: Verification
)
