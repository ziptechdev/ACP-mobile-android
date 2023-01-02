package com.acpmobile.ui.fragments.account.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acpmobile.data.model.EligibleUser
import com.acpmobile.data.model.LoginUser
import com.acpmobile.data.model.Verification
import com.acpmobile.data.repo.MainRepository
import com.acpmobile.data.request.EligibilityRegisterRequest
import com.acpmobile.data.request.EmailVerificationRequest
import com.acpmobile.data.response.EligibilityRegisterResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.Serializable
import javax.inject.Inject


@HiltViewModel
class EligibilityRegisterViewModel @Inject constructor(
    private val mainRepository: MainRepository,
) : ViewModel(), Serializable {

    val eligibleUser = MutableLiveData<EligibleUser>()
    val eligibleRegisterError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()
    val verificationCodeLiveData = MutableLiveData<Verification>()


    fun verifyEmail(request: EmailVerificationRequest) {
        viewModelScope.launch {
            loading.value = true
            val result = mainRepository.verifyEmail(request)
            if (result.isSuccessful) {
                result.body().let {
                    verificationCodeLiveData.value = it?.data
                }
                eligibleRegisterError.value = false
                loading.value = false
            } else {
                eligibleRegisterError.value = true
                loading.value = false
            }

        }
    }

    fun eligibilityRegister(eligibilityRegisterID: String, request: EligibilityRegisterRequest) {
        viewModelScope.launch {
            loading.value = true

            val result = mainRepository.eligibilityRegisterRequest(eligibilityRegisterID, request)
            if (result.isSuccessful) {
                result.body().let {
                    eligibleUser.value = it?.data
                }
                eligibleRegisterError.value = false
                loading.value = false
            } else {
                eligibleRegisterError.value = true
                loading.value = false
            }
        }
    }
}