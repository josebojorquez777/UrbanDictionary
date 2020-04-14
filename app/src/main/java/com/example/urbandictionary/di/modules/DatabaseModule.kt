package com.example.urbandictionary.di.modules

import android.app.Application
import androidx.room.Room
import com.example.urbandictionary.models.DictionaryDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * This module supplies the needed dependencies for the local cache.
 */
@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDictionaryDatabase(app: Application) = Room.databaseBuilder(
        app.applicationContext,
        DictionaryDatabase::class.java, "dictionaryDb"
    ).build()
}