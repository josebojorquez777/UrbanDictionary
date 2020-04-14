package com.example.urbandictionary.adapter

import com.example.urbandictionary.databinding.LayoutDictionaryEntryBinding
import com.example.urbandictionary.models.DictionaryEntry
import com.example.urbandictionary.viewmodel.EntryViewModel
import javax.inject.Inject

class DictionaryRecyclerAdapter @Inject constructor(
    dictionaryList: List<DictionaryEntry>,
    clickAction: (Any) -> Unit,
    layoutId: Int
): RecyclerAdapter<DictionaryEntry>(dictionaryList, clickAction, layoutId) {
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        (holder.binding as LayoutDictionaryEntryBinding).viewModel = EntryViewModel(entries[position])
    }
}