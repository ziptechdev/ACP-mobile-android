package com.acpmobile.data.response

import com.acpmobile.data.model.NationalVerifier

data class NationalVerifierResponse(
    val statusCode: Int,
    val message: String,
    val data: NationalVerifier
)
