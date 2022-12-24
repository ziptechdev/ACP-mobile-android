package com.acpmobile.ui.fragments.profile.viewmodels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    var response: MutableLiveData<LoginResponse> = MutableLiveData()

    fun userLogin(request: LoginRequest) {
        viewModelScope.launch {
            response.value = mainRepository.userLoginRequest(request).body()
        }
    }
}