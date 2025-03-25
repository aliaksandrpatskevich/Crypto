package com.test.Cryptotest.data.cache

import com.test.Cryptotest.data.model.Asset

class AssetsCache {

    var assetsCached: List<Asset>? = null

    fun getAssets(): List<Asset>? {
        return assetsCached //in memory caching, can be extended to Room
    }
}