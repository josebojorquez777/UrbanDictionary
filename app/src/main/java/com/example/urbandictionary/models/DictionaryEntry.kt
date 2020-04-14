package com.example.urbandictionary.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DictionaryEntry (
    val definition: String,
    val permalink: String,
    val thumbs_up: Int,
    val author: String,
    val word: String,
    @PrimaryKey
    val defid: Int,
    val example: String,
    val thumbs_down: Int
)