package com.test.crypto

import com.test.Cryptotest.data.cache.AssetsCache
import com.test.Cryptotest.data.repository.AssetsRepository
import com.test.Cryptotest.data.repository.AssetsRepositoryImpl
import com.test.crypto.data.api.CryptoApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object CryptoModule {

    //    can be injected using product flavors
    //    private val baseUrl = BuildConfig.baseURL
    val baseUrl = "https://api.coincap.io/v2/"

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): CryptoApiService =
        retrofit.create(CryptoApiService::class.java)

    @Provides
    fun provideAssetsRepository(fetchApiService: CryptoApiService, hiringsCache: AssetsCache): AssetsRepository =
        AssetsRepositoryImpl(fetchApiService, hiringsCache)

    @Provides
    fun provideAssetsCache(): AssetsCache = AssetsCache()
}