package com.acpmobile.data.model

data class NationalVerifierRequest(

    var firstName : String,
    var middleName : String,
    var lastName : String,
    var address : String,
    var state : String,
    var city : String,
    var zipCode : String,
    var phoneNumber : String,
    var dateOfBirth : String,
    var eligibilityProgramCode : String,
    var consentInd : String,
    var socialSecurityNumber : String

    )
