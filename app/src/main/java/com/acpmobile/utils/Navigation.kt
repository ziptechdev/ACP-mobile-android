package com.acpmobile.utils

import com.acpmobile.R
import com.acpmobile.ui.activity.MainActivity
import javax.inject.Inject

class Navigation @Inject constructor(){

    var activity : MainActivity? = null

    fun openWelcomeSecondFragment(){
        activity?.navController?.navigate(R.id.action_welcomeFirstFragment_to_welcomeSecondFragment)
    }

    fun openCheckEligibilityOrCreateAccount(){
        activity?.navController?.navigate(R.id.action_welcomeSecondFragment_to_checkEligibilityOrCreateAccount)
    }

    fun openCheckEligibility(){
        activity?.navController?.navigate(R.id.action_checkEligibilityOrCreateAccount_to_eligibilityCheck)
    }

    fun openCheckEligibilityName(){
        activity?.navController?.navigate(R.id.action_eligibilityCheck_to_eligibilityName)
    }

    fun openCheckEligibilityDate(){
        activity?.navController?.navigate(R.id.action_eligibilityName_to_eligibilityDateFragment)
    }

    fun openCheckEligibilityAddress(){
        activity?.navController?.navigate(R.id.action_eligibilityDateFragment_to_eligibilityAddressFragment)
    }

    fun openVerifyingFragment(){
        activity?.navController?.navigate(R.id.action_eligibilityAddressFragment_to_eligibilityVerifyingFragment)
    }

    fun openVerifyingSuccessFragment(){
        activity?.navController?.navigate(R.id.action_eligibilityVerifyingFragment_to_eligibilityVerifyingSuccessFragment)
    }

    fun back(){
        activity?.navController?.popBackStack()
    }
}