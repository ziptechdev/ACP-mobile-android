package com.acpmobile.data.request

import com.acpmobile.data.model.BankAccount
import com.acpmobile.data.model.User

data class KYCRequest(
    var id: Int? = null,
    var firstName: String? = null,
    var lastName: String? = null,
    var username: String? = null,
    var password: String? = null,
    var confirmedPassword: String? = null,
    var phoneNumber: String? = null,
    var socialSecurityNumber: String? = null,
    var bankName: String? = null,
    var bankNumber: String? = null,
    var accountHolderName: String? = null,
    var accountNumber: String? = null,
    var expirationDate: String? = null
)