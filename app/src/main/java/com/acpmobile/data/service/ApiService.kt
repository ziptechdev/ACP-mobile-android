package com.acpmobile.data.service

import com.acpmobile.data.model.KYCRequest
import com.acpmobile.data.model.KYCResponse
import com.acpmobile.data.model.NationalVerifierRequest
import com.acpmobile.data.model.NationalVerifierResponse
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

}