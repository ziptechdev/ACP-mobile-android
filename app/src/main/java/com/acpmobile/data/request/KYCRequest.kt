package com.acpmobile.data.request

import com.acpmobile.data.model.BankAccount
import com.acpmobile.data.model.User

data class KYCRequest(
    var user: User? = null,
    var bankAccount: BankAccount? = null
)