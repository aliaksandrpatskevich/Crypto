package com.test.crypto.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.test.crypto.ui.viewmodel.AssetsViewModel

@Composable
fun CryptoApp() {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        val assetsViewModel: AssetsViewModel = viewModel()
        HomeScreen(
            assetsUiState = assetsViewModel.assetsUiState
        )
    }
}