package com.example.urbandictionary.network

import com.example.urbandictionary.models.DictionaryResponse
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * This retrofit service is used for requests to the dictionary api.
 */
interface DictionaryApi {
    @GET("define")
    fun searchForWord(@Query("term")query: String): Single<DictionaryResponse>
}