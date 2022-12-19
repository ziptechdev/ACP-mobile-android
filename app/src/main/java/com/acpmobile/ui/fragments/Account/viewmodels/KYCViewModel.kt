package com.acpmobile.ui.fragments.Account.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acpmobile.data.model.KYCRequest
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
            val result =  mainRepository.kycRequest(request)
            Log.i("DADASDASDASD", result.isSuccessful.toString())
        }
    }
}