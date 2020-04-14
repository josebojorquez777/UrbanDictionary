package com.example.urbandictionary.di.modules

import com.example.urbandictionary.R
import com.example.urbandictionary.adapter.DictionaryRecyclerAdapter
import dagger.Module
import dagger.Provides

/**
 * This module supplies the needed dependencies for adapters for dependency injection.
 */
@Module
class AdapterModule {
    @Provides
    fun getDictionaryAdapter() = DictionaryRecyclerAdapter(mutableListOf(), {}, R.layout.layout_dictionary_entry)
}