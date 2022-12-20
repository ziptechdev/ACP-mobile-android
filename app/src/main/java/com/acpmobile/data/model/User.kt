package com.acpmobile.data.model

data class User(
    val id: Int?,
    val firstName: String?,
    val lastName: String?,
    val username: String?,
    val password: String?,
    val confirmedPassword: String?,
    val phoneNumber: String?,
    val socialSecurityNumber: String?
)