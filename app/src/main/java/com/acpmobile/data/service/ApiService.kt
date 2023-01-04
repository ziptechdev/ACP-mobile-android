package com.acpmobile.data.service
import com.acpmobile.data.request.*
import com.acpmobile.data.response.*
import retrofit2.Response
import retrofit2.http.*

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

    @Headers("Content-Type:application/json")
    @POST("users/eligibility-register/{eligibilityCheckId}")
    suspend fun eligibilityRegister(@Path("eligibilityCheckId") eligibilityRegisterID: String,
                                    @Body request: EligibilityRegisterRequest): Response<EligibilityRegisterResponse>

    @Headers("Content-Type:application/json")
    @POST("users/login")
    suspend fun userLogin(@Body request: LoginRequest): Response<LoginResponse>

    @Headers("Content-Type:application/json")
    @POST("users/logout")
    suspend fun userLogout(@Header("Authorization") token: String ): Response<LogoutResponse>

}