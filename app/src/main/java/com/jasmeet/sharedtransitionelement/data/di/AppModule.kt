package com.jasmeet.sharedtransitionelement.data.di

import com.jasmeet.sharedtransitionelement.data.apiService.ApiService
import com.jasmeet.sharedtransitionelement.data.repository.ApiResponseRepository
import com.jasmeet.sharedtransitionelement.data.repositoryImpl.ApiResponseRepoImpl
import com.jasmeet.sharedtransitionelement.domain.useCase.ApiResponseUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService {

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(loggingInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(ApiService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesApiResponseRepository(apiService: ApiService): ApiResponseRepository =
        ApiResponseRepoImpl(apiService)

    @Provides
    @Singleton
    fun providesTrendingMoviesUseCase(repository: ApiResponseRepository): ApiResponseUseCase =
        ApiResponseUseCase(repository)
}