package com.acpmobile.ui.fragments.account.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acpmobile.data.model.UserVerification
import com.acpmobile.data.repo.MainRepository
import com.acpmobile.data.request.UserVerificationRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.io.Serializable
import javax.inject.Inject

@HiltViewModel
class VerificationViewModel @Inject constructor(
    private val mainRepository: MainRepository,
) : ViewModel(), Serializable {

    val verifySuccess = MutableLiveData<UserVerification>()
    val loginUserError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun userVerification(request: UserVerificationRequest, attachment: File?, attachment1: File?, attachment2: File?) {


        var attachmentBody : MultipartBody.Part? = null
        if (attachment != null){
            attachmentBody = MultipartBody.Part.createFormData(
                "", attachment.getName(), RequestBody.create(
                    "multipart/form-data".toMediaTypeOrNull(), attachment
                ))
        }

        var attachmentBody1 : MultipartBody.Part? = null
        if (attachment1 != null){
            attachmentBody1 = MultipartBody.Part.createFormData(
                "", attachment1.getName(), RequestBody.create(
                    "multipart/form-data".toMediaTypeOrNull(), attachment1
                ))
        }

        var attachmentBody2 : MultipartBody.Part? = null
        if (attachment2 != null){
            attachmentBody2 = MultipartBody.Part.createFormData(
                "", attachment2.getName(), RequestBody.create(
                    "multipart/form-data".toMediaTypeOrNull(), attachment2
                ))
        }

        request.documentIdFront = attachmentBody
        request.documentIdBack = attachmentBody1
        request.selfie = attachmentBody2

        viewModelScope.launch {
            loading.value = true
            val result = mainRepository.userVerification(request)
            if (result.isSuccessful) {
                result.body().let {
                    verifySuccess.value = it?.data
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