package com.acpmobile.data.model

data class KYCRequest(
    var user: User? = null,
    var bankAccount: BankAccount? = null
)