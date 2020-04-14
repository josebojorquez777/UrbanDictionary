package com.example.urbandictionary.models

import io.reactivex.Single

interface Repository {
    fun searchForWord(query: String): Single<List<DictionaryEntry>>

    fun saveToLocalCache(entryList: List<DictionaryEntry>)

    fun addPreviouslySearchedWord(word: String)
}