package com.acpmobile.data.response

import com.acpmobile.data.model.Verification

data class EmailVerificationResponse(
    val statusCode: Int,
    val message: String,
    val data: Verification
)
