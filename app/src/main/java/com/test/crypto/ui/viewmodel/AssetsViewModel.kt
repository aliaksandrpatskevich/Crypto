package com.test.crypto.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.Cryptotest.data.model.Asset
import com.test.Cryptotest.data.repository.AssetsRepository

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@HiltViewModel
class AssetsViewModel @Inject constructor(
    private val repository: AssetsRepository
) : ViewModel() {
    var assetsUiState: AssetsUiState by mutableStateOf(AssetsUiState.Loading)
        private set


    fun getAssets() {
        viewModelScope.launch {
            assetsUiState = AssetsUiState.Loading
            assetsUiState = try {
                AssetsUiState.Success(repository.getAssets())
            } catch (e: IOException) {
                AssetsUiState.Error
            } catch (e: HttpException) {
                AssetsUiState.Error
            }
        }
    }
}

sealed interface AssetsUiState {
    data class Success(val assets: List<Asset>) : AssetsUiState
    object Error : AssetsUiState
    object Loading : AssetsUiState
}