package com.acpmobile.data.service
import com.acpmobile.data.request.*
import com.acpmobile.data.response.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @Headers("Content-Type:application/json")
    @POST("national-verifier/eligibility-check")
    suspend fun nationalVerifier(@Body request: NationalVerifierRequest): Response<NationalVerifierResponse>

    @Headers("Content-Type:application/json")
    @POST("users/kyc-register/account-id/{accountId}/workflow-execution-id/{workflowExecutionId}")
    suspend fun kycRegister(@Path("accountId") accountID: String, @Path("workflowExecutionId") workflowExecutionID: String, @Body request: KYCRequest): Response<KYCResponse>

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


    @Multipart
    @POST("jumio/resident-identity-verification")
    suspend fun userVerification(
        @Part("username") username: RequestBody,
        @Part("userIp") userIp: RequestBody,
        @Part("consentOptained") consentOptained: RequestBody,
        @Part("consentOptainedAt") consentOptainedAt: RequestBody,
        @Part("userState") userState: RequestBody,

        @Part file :MultipartBody.Part,
        @Part file2 :MultipartBody.Part,
        @Part file3 :MultipartBody.Part
    ): Response<UserVerificationResponse>

    @POST("users/logout")
    suspend fun userLogout(@Header("Authorization") token: String): Response<LogoutResponse>


}