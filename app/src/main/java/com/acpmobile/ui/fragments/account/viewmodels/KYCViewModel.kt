package com.acpmobile.ui.fragments.account.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    fun kycRegister(request: KYCRequest) {
        viewModelScope.launch {
            val result = mainRepository.kycRequest(request)
        }
    }

    fun verifyEmail(request: EmailVerificationRequest) {
        viewModelScope.launch {
            val result = mainRepository.verifyEmail(request)
        }
    }
}