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

    fun openCreateNewAccountFragment(){
        activity?.navController?.navigate(R.id.action_checkEligibilityOrCreateAccount_to_createNewAccountFragment)
    }

    fun openPersonalInfoFragment(){
        activity?.navController?.navigate(R.id.action_createNewAccountFragment_to_personalInfoFragment)
    }

    fun openIdentityProof(){
        activity?.navController?.navigate(R.id.identityProofFragment)
    }

    fun openScanIdFragment(){
        activity?.navController?.navigate(R.id.action_identityProofFragment_to_scanIdFragment)
    }

    fun openTakeSelfieFragment(){
        activity?.navController?.navigate(R.id.action_scanIdFragment_to_TakeSelfieFragment)
    }

    fun openSuccessIdentityProof(){
        activity?.navController?.navigate(R.id.action_TakeSelfieFragment_to_fragmentIdentityProofSuccess)
    }

    fun openBankInfo(){
        activity?.navController?.navigate(R.id.action_fragmentIdentityProofSuccess_to_fragmentIBankInfo)
    }

    fun openRegistrationComplete(){
        activity?.navController?.navigate(R.id.action_fragmentIBankInfo_to_fragmentRegistrationCompleteEligibility)
    }

    fun openVerificationSuccess(){
        activity?.navController?.navigate(R.id.action_eligibilityVerifyingFragment_to_eligibilityVerifyingSuccessFragment)
    }

    fun openVerificationFailed(){
        activity?.navController?.navigate(R.id.action_eligibilityVerifyingFragment_to_eligibilityVerificationFailedFragment)
    }

    fun openRegisterNewAccount(){
        activity?.navController?.navigate(R.id.action_eligibilityVerifyingSuccessFragment_to_fragmentRegisterNewAccount)
    }

    fun openRegisterNewAccountComplete(){
        activity?.navController?.navigate(R.id.action_fragmentRegisterNewAccount_to_fragmentRegistrationCompleteEligibility)
    }

    fun openLogin(){
        activity?.navController?.navigate(R.id.action_fragmentRegistrationCompleteEligibility_to_fragmentLogin)
    }

    fun back(){
        activity?.navController?.popBackStack()
    }
}