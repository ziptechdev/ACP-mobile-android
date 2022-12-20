package com.acpmobile.data.repo

import com.acpmobile.data.model.*
import com.acpmobile.data.service.ApiService
import retrofit2.Response
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun nationalVerifierRequest(requst: NationalVerifierRequest): Response<NationalVerifierResponse> {
        return apiService.nationalVerifier(requst)
    }

    suspend fun kycRequest(request: KYCRequest): Response<KYCResponse> {
        return apiService.kycRegister(request)
    }

    suspend fun verifyEmail(request: EmailVerificationRequest): Response<EmailVerificationResponse> {
        return apiService.verifyEmail(request)
    }
}
