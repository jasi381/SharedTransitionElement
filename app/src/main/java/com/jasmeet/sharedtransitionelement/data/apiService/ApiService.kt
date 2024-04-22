package com.jasmeet.sharedtransitionelement.data.apiService

import com.jasmeet.sharedtransitionelement.data.response.ApiResponseItem
import retrofit2.http.GET

interface ApiService {
    companion object{
        const val BASE_URL = "https://picsum.photos/v2/"
    }

    @GET("list")
    suspend fun getAllData() :  List<ApiResponseItem>
}