package com.acpmobile.data.response

import com.acpmobile.data.model.LoginUser

class LoginResponse(
    val statusCode: Int,
    val message: String,
    val data: LoginUser
)