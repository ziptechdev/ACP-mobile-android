package com.acpmobile.data.response

import com.acpmobile.data.model.EligibleUser

data class EligibilityRegisterResponse(
    val statusCode: Int,
    val message: String,
    val data: EligibleUser
)