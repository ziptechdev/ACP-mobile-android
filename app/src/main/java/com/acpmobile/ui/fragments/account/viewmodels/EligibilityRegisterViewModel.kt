package com.acpmobile.ui.fragments.account.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acpmobile.data.repo.MainRepository
import com.acpmobile.data.request.EligibilityRegisterRequest
import com.acpmobile.data.response.EligibilityRegisterResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.Serializable
import javax.inject.Inject


@HiltViewModel
class EligibilityRegisterViewModel @Inject constructor(
    private val mainRepository: MainRepository,
) : ViewModel(), Serializable {

    var response: MutableLiveData<EligibilityRegisterResponse> = MutableLiveData()

    fun eligibilityRegister(eligibilityRegisterID: String, request: EligibilityRegisterRequest) {
        viewModelScope.launch {
            response.value = mainRepository.eligibilityRegisterRequest(eligibilityRegisterID, request).body()
        }
    }
}