package com.acpmobile.data.model

data class EligibleUser(
    val id: Int?,
    val username: String?,
    val firstName: String?,
    val lastName: String?,
    val middleName: String?,
    val eligibilityCheckId: String?,
    val applicationId: String?,
    val eligibilityCheckStatus: String?
)