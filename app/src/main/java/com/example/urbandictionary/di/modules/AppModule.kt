package com.example.urbandictionary.di.modules

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * This module supplies the application dependency.
 */
@Module
class AppModule(private val app: Application) {

    @Singleton
    @Provides
    fun getApplication(): Application = app
}