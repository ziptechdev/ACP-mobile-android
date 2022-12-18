package com.acpmobile.data.repo

import com.acpmobile.data.model.NationalVerifierRequest
import com.acpmobile.data.model.NationalVerifierResponse
import com.acpmobile.data.service.ApiService
import retrofit2.Response
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun nationalVerifierRequest(expenseId: NationalVerifierRequest): Response<NationalVerifierResponse> {
        return apiService.nationalVerifier(expenseId)
    }
}