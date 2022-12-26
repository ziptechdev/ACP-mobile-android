package com.acpmobile.data.repo

import com.acpmobile.data.request.*
import com.acpmobile.data.response.*
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

    suspend fun eligibilityRegisterRequest(eligibilityRegisterID: String, request: EligibilityRegisterRequest):Response<EligibilityRegisterResponse>{
        return apiService.eligibilityRegister(eligibilityRegisterID, request)
    }

    suspend fun userLoginRequest(request: LoginRequest): Response<LoginResponse>{
        return apiService.userLogin(request)
    }
}
