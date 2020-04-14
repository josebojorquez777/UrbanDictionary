package com.example.urbandictionary

import com.example.urbandictionary.di.modules.NetworkModule
import com.example.urbandictionary.network.DictionaryApi
import io.mockk.mockk
import okhttp3.OkHttpClient

class TestModule : NetworkModule(){
    /**
     * Provide a mock instead of actual api service
     */
    override fun provideDictionaryApi(okHttpClient: OkHttpClient): DictionaryApi = mockk()

}