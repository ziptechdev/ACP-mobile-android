package com.acpmobile.data.response

import com.acpmobile.data.model.UserVerification

data class UserVerificationResponse(
    val statusCode: Int,
    val message: String,
    val data: UserVerification
)