package com.acpmobile.data.repo

import com.acpmobile.data.request.*
import com.acpmobile.data.response.*
import com.acpmobile.data.service.ApiService
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Response
import javax.inject.Inject




class MainRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun nationalVerifierRequest(request: NationalVerifierRequest): Response<NationalVerifierResponse> {
        return apiService.nationalVerifier(request)
    }

    suspend fun kycRequest(accountID: String, workflowExecutionID : String, request: KYCRequest): Response<KYCResponse> {
        return apiService.kycRegister(accountID, workflowExecutionID, request)
    }

    suspend fun verifyEmail(request: EmailVerificationRequest): Response<EmailVerificationResponse> {
        return apiService.verifyEmail(request)
    }

    suspend fun eligibilityRegisterRequest(eligibilityRegisterID: String, request: EligibilityRegisterRequest):Response<EligibilityRegisterResponse>{
        return apiService.eligibilityRegister(eligibilityRegisterID, request)
    }

    suspend fun userLoginRequest(request: LoginRequest): Response<LoginResponse>{
        return apiService.userLogin(request)
    }

    suspend fun userVerification(
        user: UserVerificationRequest
    ): Response<UserVerificationResponse> {
        val requestBodyUserName = createPartFromString(user.username)
        val requestBodyUserIp= createPartFromString(user.userIp)
        val requestBodyConsentOptained = createPartFromString(user.consentOptained)
        val requestBodyConsentOptainedAt = createPartFromString(user.consentOptainedAt)
        val requestState = createPartFromString(user.userState)

        var attachmentBody : MultipartBody.Part? = null


        var map : HashMap<String, RequestBody> = HashMap()

        map["username"] = requestBodyUserName!!
        map["userIp"] = requestBodyUserIp!!
        map["consentOptained"] = requestBodyConsentOptained!!
        map["consentOptainedAt"] = requestBodyConsentOptainedAt!!
        map["userState"] = requestState!!

//        val request = RequestBody.create(MultipartBody.FORM, user)
        return apiService.userVerification(requestBodyUserName, requestBodyUserIp, requestBodyConsentOptained, requestBodyConsentOptainedAt,
            requestState,
            user.documentIdFront!!, user.documentIdBack!!, user.selfie!!)

    }

    fun createPartFromString(string: String?): RequestBody? {
        return RequestBody.create("text/plain".toMediaTypeOrNull(), string!!)
    }
}
