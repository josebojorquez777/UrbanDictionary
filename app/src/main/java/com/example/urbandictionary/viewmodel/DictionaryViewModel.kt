package com.example.urbandictionary.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.urbandictionary.adapter.DictionaryRecyclerAdapter
import com.example.urbandictionary.models.DictionaryEntry
import com.example.urbandictionary.models.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

const val ZERO = 0
class DictionaryViewModel @Inject constructor(
    private val repo: Repository,
    private val app: Application,
    private val dictionaryAdapter: DictionaryRecyclerAdapter
): ViewModel() {

    /**
     * Observable fields for data binding.
     */
    val adapter = ObservableField<DictionaryRecyclerAdapter>(dictionaryAdapter)
    val slangWord = ObservableField<String>()
    /**
     * Composite disposable for easily cleaning up disposables.
     */
    private val disposables = CompositeDisposable()

    /**
     * Function triggers the search for definition entries for the word from the repo.
     */
    fun searchWord() {
        slangWord.get()?.isNotEmpty()?.let {
            if(it){
                slangWord.get()?.let {
                    disposables.add(repo.searchForWord(it)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                            { response -> onSuccessfulWordSearch(response)},
                            { error -> onFailureWordSearch() }
                        )
                    )
                }
            }
        }
    }

    /**
     * This function is triggered when the data is successfully retrieved from the repo
     */
    private fun onSuccessfulWordSearch(response: List<DictionaryEntry>) {
        if(response.isNotEmpty()) {
            repo.addPreviouslySearchedWord(response[ZERO].word.toLowerCase())
            dictionaryAdapter.entries = response
            dictionaryAdapter.notifyDataSetChanged()
            repo.saveToLocalCache(response)
        } else {
            onFailureWordSearch()
        }
    }

    /**
     * This function is triggered when the data is not successfully retrieved from the repo
     */
    private fun onFailureWordSearch() {
        Toast.makeText(app.applicationContext, "Search for word Failed", Toast.LENGTH_SHORT).show()
    }

    /**
     * This function sorts the list of the recycler view by up votes.
     */
    fun sortByThumbsUp() {
        dictionaryAdapter.entries = dictionaryAdapter.entries.sortedByDescending { it.thumbs_up }
        dictionaryAdapter.notifyDataSetChanged()
    }

    /**
     * This function sorts the list of the recycler view by down votes.
     */
    fun sortByThumbsDown() {
        dictionaryAdapter.entries = dictionaryAdapter.entries.sortedByDescending { it.thumbs_down }
        dictionaryAdapter.notifyDataSetChanged()
    }

    /**
     * Clears the composite disposable.
     */
    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }
}