package com.acpmobile.data.response

import com.acpmobile.data.model.Link

data class NationalVerifierResponse(
    val eligibilityCheckId: String,
    val applicationId: String,
    val status: String,
    val _links: Link
)
