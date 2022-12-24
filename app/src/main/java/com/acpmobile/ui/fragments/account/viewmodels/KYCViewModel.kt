package com.acpmobile.ui.fragments.account.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acpmobile.data.model.Verification
import com.acpmobile.data.request.EmailVerificationRequest
import com.acpmobile.data.request.KYCRequest
import com.acpmobile.data.repo.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.Serializable
import javax.inject.Inject

@HiltViewModel
class KYCViewModel @Inject constructor(
    private val mainRepository: MainRepository,
) : ViewModel(), Serializable {

    val verificationCode = MutableLiveData<Verification>()
    val verificationError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun kycRegister(request: KYCRequest) {
        viewModelScope.launch {
            val result = mainRepository.kycRequest(request)
        }
    }

    fun verifyEmail(request: EmailVerificationRequest) {
        viewModelScope.launch {
            loading.value = true
            val result = mainRepository.verifyEmail(request)
            if (result.isSuccessful) {
                result.body().let {
                    verificationCode.value = it?.data
                }
                verificationError.value = false
                loading.value = false
            } else {
                verificationError.value = true
                loading.value = false
            }

        }
    }
}