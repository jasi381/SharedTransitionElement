package com.jasmeet.sharedtransitionelement.domain.useCase

import com.jasmeet.sharedtransitionelement.data.repository.ApiResponseRepository

class ApiResponseUseCase(private val repository: ApiResponseRepository) {
    suspend operator  fun invoke() = repository.getData()
}