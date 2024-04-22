package com.jasmeet.sharedtransitionelement.data.repository

import com.jasmeet.sharedtransitionelement.data.response.ApiResponseItem

interface ApiResponseRepository {
    suspend fun getData(): List<ApiResponseItem>
}