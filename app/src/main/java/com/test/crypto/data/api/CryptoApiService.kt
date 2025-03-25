package com.test.crypto.data.api

import com.test.Cryptotest.data.model.GetAssetsResult
import retrofit2.http.GET

interface CryptoApiService {
    @GET("assets")
    suspend fun getAssets(): GetAssetsResult
}