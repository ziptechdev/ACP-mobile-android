package com.acpmobile.ui.fragments.Account.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acpmobile.data.model.NationalVerifierRequest
import com.acpmobile.data.repo.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.Serializable
import javax.inject.Inject

@HiltViewModel
class NationalVerifierViewModel @Inject constructor(
    private val mainRepository: MainRepository,
) : ViewModel(), Serializable {

    fun nationalVerifier(request: NationalVerifierRequest) {
        viewModelScope.launch {
            val result =  mainRepository.nationalVerifierRequest(request)
        }
    }
}