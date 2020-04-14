package com.example.urbandictionary.di.modules

import com.example.urbandictionary.models.DictionaryDatabase
import com.example.urbandictionary.models.Repository
import com.example.urbandictionary.models.RepositoryImpl
import com.example.urbandictionary.network.DictionaryApi
import com.example.urbandictionary.util.api_key
import com.example.urbandictionary.util.base_url
import com.example.urbandictionary.util.host
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * This module supplies the needed dependencies for our network operations
 * like the okhttp client and retrofit service.
 */
@Module
open class NetworkModule {
    @Singleton
    @Provides
    fun provideOkhttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("x-rapidapi-host", host)
                .addHeader("x-rapidapi-key", api_key)
                .build()
            chain.proceed(request)
        }
        .build()

    @Singleton
    @Provides
    open fun provideDictionaryApi(okHttpClient: OkHttpClient): DictionaryApi = Retrofit.Builder()
        .baseUrl(base_url)
        .client(okHttpClient)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(DictionaryApi::class.java)

    @Singleton
    @Provides
    fun provideRepository(api: DictionaryApi, cache: DictionaryDatabase): Repository =
        RepositoryImpl(api, cache)
}