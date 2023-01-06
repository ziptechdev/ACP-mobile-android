package com.acpmobile.data.model

data class NationalVerifier(
    val eligibilityCheckId: String,
    val applicationId: String,
    val status: String,
    val _links: Link
)
