package com.test.Cryptotest.data.repository

import com.test.Cryptotest.data.cache.AssetsCache
import com.test.Cryptotest.data.model.Asset
import com.test.crypto.data.api.CryptoApiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AssetsRepositoryImpl @Inject constructor(
    private val cryptoApiService: CryptoApiService,
    private val assetsCache: AssetsCache,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) : AssetsRepository {

    override suspend fun getAssets(): List<Asset> {
        val useCache = true

        return withContext(defaultDispatcher) {
            assetsCache.assetsCached?.let { if (useCache) return@withContext it }

            val assets = cryptoApiService.getAssets().data
            assetsCache.assetsCached = assets

            return@withContext assets
        }
    }
}