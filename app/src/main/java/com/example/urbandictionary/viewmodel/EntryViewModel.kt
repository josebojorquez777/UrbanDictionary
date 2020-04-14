package com.example.urbandictionary.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.urbandictionary.models.DictionaryEntry

/**
 * This view model provides data to the items in recycler view.
 */

class EntryViewModel constructor(entry: DictionaryEntry): ViewModel() {
    val definition = ObservableField<String>(entry.definition)
    val author = ObservableField<String>(entry.author)
    val upVotes = ObservableField<String>(entry.thumbs_up.toString())
    val downVotes = ObservableField<String>(entry.thumbs_down.toString())
}