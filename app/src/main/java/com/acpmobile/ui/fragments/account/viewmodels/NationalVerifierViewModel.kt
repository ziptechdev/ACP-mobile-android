package com.acpmobile.ui.fragments.account.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acpmobile.data.request.NationalVerifierRequest
import com.acpmobile.data.response.NationalVerifierResponse
import com.acpmobile.data.repo.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.Serializable
import javax.inject.Inject

@HiltViewModel
class NationalVerifierViewModel @Inject constructor(
    private val mainRepository: MainRepository,
) : ViewModel(), Serializable {

    var response = MutableLiveData<NationalVerifierResponse>()
    val error = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun nationalVerifier(request: NationalVerifierRequest) {
        viewModelScope.launch {
            loading.value = true
            val res = mainRepository.nationalVerifierRequest(request)
            if (res.isSuccessful) {
                res.body().let {
                    response.value = it
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