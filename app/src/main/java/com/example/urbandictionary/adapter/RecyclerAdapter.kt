package com.example.urbandictionary.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.urbandictionary.models.DictionaryEntry

abstract class RecyclerAdapter<T> constructor(
    var entries: List<T>,
    val click: (Any) -> Unit,
    val itemLayout: Int
): RecyclerView.Adapter<RecyclerAdapter<T>.ItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val dataBinding: ViewDataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            itemLayout,
            parent,
            false)
        return ItemViewHolder(dataBinding)
    }

    override fun getItemCount(): Int {
        return entries.size
    }

    inner class ItemViewHolder constructor(val binding: ViewDataBinding): RecyclerView.ViewHolder(binding.root) {
        fun setEntryClick(item: Any) {
            binding.root.setOnClickListener { click(item) }
        }
    }
}