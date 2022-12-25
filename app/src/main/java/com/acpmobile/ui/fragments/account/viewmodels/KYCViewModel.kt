package com.acpmobile.ui.fragments.account.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acpmobile.data.model.User
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

    val verificationCodeLiveData = MutableLiveData<Verification>()
    val error = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()
    val userLiveData = MutableLiveData<User>()

    fun kycRegister(request: KYCRequest) {
        viewModelScope.launch {
            loading.value = true
            val result = mainRepository.kycRequest(request)
            if (result.isSuccessful) {
                result.body().let {
                    userLiveData.value = it?.data
                }
                error.value = false
                loading.value = false
            } else {
                error.value = true
                loading.value = false
            }

        }
    }

    fun verifyEmail(request: EmailVerificationRequest) {
        viewModelScope.launch {
            loading.value = true
            val result = mainRepository.verifyEmail(request)
            if (result.isSuccessful) {
                result.body().let {
                    verificationCodeLiveData.value = it?.data
                }
                error.value = false
                loading.value = false
            } else {
                error.value = true
                loading.value = false
            }

        }
    }
}