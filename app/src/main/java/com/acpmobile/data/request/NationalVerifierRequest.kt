package com.acpmobile.data.request

data class NationalVerifierRequest(

    var firstName : String? = null,
    var middleName : String? = null,
    var lastName : String? = null,
    var address : String? = null,
    var state : String? = null,
    var city : String? = null,
    var zipCode : String? = null,
    var phoneNumber : String? = null,
    var dateOfBirth : String? = null,
    var eligibilityProgramCode : String? = null,
    var consentInd : String? = null,
    var socialSecurityNumber : String? = null

    )
