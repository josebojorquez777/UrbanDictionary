package com.example.urbandictionary.models

import android.util.Log
import com.example.urbandictionary.network.DictionaryApi
import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import kotlin.collections.HashSet

const val ZERO = 0
class RepositoryImpl @Inject constructor(
    private val dictionaryApi: DictionaryApi,
    private val localCache: DictionaryDatabase
): Repository {
    /**
     * These sets are used to keep track of the words that have been searched and cached.
     */
    private val previouslySearchedWords = HashSet<String>()
    private val cachedWords = HashSet<String>()

    override fun addPreviouslySearchedWord(word: String) {
        previouslySearchedWords.add(word)
    }

    /**
    This function is used to source the entries for a word either from the local database cache
     or from the public api.
     */
    override fun searchForWord(query: String): Single<List<DictionaryEntry>> {
        return if(previouslySearchedWords.contains(query)) {
            Log.d("REPO", "pulling from cache")
            localCache.dictionaryEntryDao().getEntrybyWord(query).toSingle()
        } else {
            previouslySearchedWords.add(query)
            dictionaryApi.searchForWord(query).map { it.list }
        }
    }

    /**
     * Logs each entry inserted successfully into the local cache.
     */
    private fun onSuccessfulInsert() {
        Log.d("REPO", "entry successfully inserted into database")
    }

    /**
     * If the insert to the cache fails then log it.
     */
    private fun onFailureInsert() {
        Log.d("REPO", "entry failed to be inserted into database")
    }

    /**
     * Save entries received from the network to the cache.
     */
    override fun saveToLocalCache(entryList: List<DictionaryEntry>) {
        if(cachedWords.contains(entryList[ZERO].word.toLowerCase())){
            return
        } else {
            entryList.forEach {
                Maybe.fromCallable { localCache.dictionaryEntryDao().insertDictionaryEntry(it) }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSuccess{cachedWords.add(entryList[ZERO].word.toLowerCase())}
                    .subscribe({onSuccessfulInsert()},
                        {onFailureInsert()})
            }
        }
    }
}