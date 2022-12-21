package com.acpmobile.data.repo

import com.acpmobile.data.request.EmailVerificationRequest
import com.acpmobile.data.request.KYCRequest
import com.acpmobile.data.request.NationalVerifierRequest
import com.acpmobile.data.response.EmailVerificationResponse
import com.acpmobile.data.response.KYCResponse
import com.acpmobile.data.response.NationalVerifierResponse
import com.acpmobile.data.service.ApiService
import retrofit2.Response
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun nationalVerifierRequest(request: NationalVerifierRequest): Response<NationalVerifierResponse> {
        return apiService.nationalVerifier(request)
    }

    suspend fun kycRequest(request: KYCRequest): Response<KYCResponse> {
        return apiService.kycRegister(request)
    }

    suspend fun verifyEmail(request: EmailVerificationRequest): Response<EmailVerificationResponse> {
        return apiService.verifyEmail(request)
    }
}
