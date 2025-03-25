package com.test.crypto.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.test.crypto.ui.screens.CryptoApp
import com.test.crypto.ui.theme.CryptoTestTheme
import com.test.crypto.ui.viewmodel.AssetsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val assetsViewModel: AssetsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptoTestTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    CryptoApp()
                }
            }
        }

        assetsViewModel.getAssets()
    }
}