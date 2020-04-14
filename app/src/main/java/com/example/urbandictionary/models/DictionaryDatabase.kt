package com.example.urbandictionary.models

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DictionaryEntry::class], version = 1)
abstract class DictionaryDatabase: RoomDatabase() {
    abstract fun dictionaryEntryDao(): DictionaryEntryDao
}