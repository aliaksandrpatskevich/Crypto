package com.test.Cryptotest.data.repository

import com.test.Cryptotest.data.cache.AssetsCache
import com.test.Cryptotest.data.model.Asset
import com.test.crypto.data.api.CryptoApiService
import javax.inject.Inject

class AssetsRepositoryImpl @Inject constructor(
    private val cryptoApiService: CryptoApiService,
    private val assetsCache: AssetsCache
) : AssetsRepository {

    override suspend fun getAssets(): List<Asset> {
        val useCache = true

        assetsCache.assetsCached?.let { if (useCache) return it }

        val assets = cryptoApiService.getAssets().data
        assetsCache.assetsCached = assets

        return assets
    }
}