package com.test.Cryptotest.data.repository

import com.test.Cryptotest.data.model.Asset

interface AssetsRepository {
    suspend fun getAssets(): List<Asset>
}