package com.acpmobile.data.service

import com.acpmobile.data.model.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {

    @Headers("Content-Type:application/json")
    @POST("national-verifier/eligibility-check")
    suspend fun nationalVerifier(@Body request: NationalVerifierRequest): Response<NationalVerifierResponse>

    @Headers("Content-Type:application/json")
    @POST("users/kyc-register")
    suspend fun kycRegister(@Body request: KYCRequest): Response<KYCResponse>

    @Headers("Content-Type:application/json")
    @POST("users/verify-email")
    suspend fun verifyEmail(@Body request: EmailVerificationRequest): Response<EmailVerificationResponse>

}