package com.acpmobile.ui.fragments.profile.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acpmobile.data.repo.MainRepository
import com.acpmobile.data.response.LogoutResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.Serializable
import javax.inject.Inject

@HiltViewModel
class LogoutViewModel @Inject constructor(
    private val mainRepository: MainRepository,
) : ViewModel(), Serializable {
    val logoutUser = MutableLiveData<LogoutResponse>()
    val logoutUserError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun userLogout(TOKEN: String) {
        viewModelScope.launch {
            loading.value = true
            val result = mainRepository.userLogoutRequest(TOKEN)
            if (result.isSuccessful) {
                result.body().let {
                    logoutUser.value = it
                }
                logoutUserError.value = false
                loading.value = false
            } else {
                logoutUserError.value = true
                loading.value = false
            }
        }
    }
}