package com.example.urbandictionary.models

import androidx.room.*
import io.reactivex.Maybe

@Dao
interface DictionaryEntryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDictionaryEntry(entry: DictionaryEntry)

    @Update
    fun updateDictionaryEntry(entry: DictionaryEntry)

    @Delete
    fun deleteDictionaryEntry(entry: DictionaryEntry)

    @Query("SELECT * FROM DictionaryEntry WHERE LOWER(word) == LOWER((:word))")
    fun getEntrybyWord(word: String): Maybe<List<DictionaryEntry>>
}