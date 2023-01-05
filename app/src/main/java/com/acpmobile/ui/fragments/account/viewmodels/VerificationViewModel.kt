package com.acpmobile.ui.fragments.account.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acpmobile.data.model.UserVerification
import com.acpmobile.data.repo.MainRepository
import com.acpmobile.data.request.UserVerificationRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.Serializable
import java.lang.Exception
import javax.inject.Inject


@HiltViewModel
class VerificationViewModel @Inject constructor(
    private val mainRepository: MainRepository,
) : ViewModel(), Serializable {

    val verifySuccess = MutableLiveData<UserVerification>()
    val loginUserError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun userVerification(
        request: UserVerificationRequest,
        attachment: File?,
        attachment1: File?,
        attachment2: File?
    ) {

        val requestFile: RequestBody = attachment!!
            .asRequestBody("image/jpeg".toMediaTypeOrNull())
        val attachmentBody: MultipartBody.Part =
            MultipartBody.Part.createFormData("documentIdFront", attachment.name, requestFile)
        val attachmentBody1: MultipartBody.Part =
            MultipartBody.Part.createFormData("documentIdBack", attachment.name, requestFile)
        val attachmentBody2: MultipartBody.Part =
            MultipartBody.Part.createFormData("selfie", attachment.name, requestFile)


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