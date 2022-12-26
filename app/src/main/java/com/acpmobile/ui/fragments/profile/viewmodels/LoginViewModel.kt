package com.acpmobile.ui.fragments.profile.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acpmobile.data.model.LoginUser
import com.acpmobile.data.repo.MainRepository
import com.acpmobile.data.request.LoginRequest
import com.acpmobile.data.response.LoginResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.Serializable
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val mainRepository: MainRepository,
) : ViewModel(), Serializable {
    val loginUser = MutableLiveData<LoginUser>()
    val loginUserError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun userLogin(request: LoginRequest) {
        viewModelScope.launch {
            loading.value = true
            val result = mainRepository.userLoginRequest(request)
            if (result.isSuccessful) {
                result.body().let {
                    loginUser.value = it?.data
                }
                loginUserError.value = false
                loading.value = false
            } else {
                loginUserError.value = true
                loading.value = false
            }
        }
    }
}