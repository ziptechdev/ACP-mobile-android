package com.acpmobile.data.service

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

}