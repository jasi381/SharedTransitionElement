package com.jasmeet.sharedtransitionelement.data.repositoryImpl

import com.jasmeet.sharedtransitionelement.data.apiService.ApiService
import com.jasmeet.sharedtransitionelement.data.repository.ApiResponseRepository
import com.jasmeet.sharedtransitionelement.data.response.ApiResponseItem

class ApiResponseRepoImpl(private val apiService: ApiService):ApiResponseRepository {
    override suspend fun getData(): List<ApiResponseItem> {
        return apiService.getAllData()
    }
}