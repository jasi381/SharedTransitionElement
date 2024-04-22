package com.jasmeet.sharedtransitionelement.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jasmeet.sharedtransitionelement.data.repository.ApiResponseRepository
import com.jasmeet.sharedtransitionelement.data.response.ApiResponseItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel  @Inject constructor(
    private val repo :ApiResponseRepository
): ViewModel() {

    private val _response :MutableStateFlow<List<ApiResponseItem?>> = MutableStateFlow(emptyList())
    val response = _response.asStateFlow()

    init {
        viewModelScope.launch {
            _response.value = repo.getData()
        }
    }
}