package com.example.urbandictionary.di.daggercomponents

import com.example.urbandictionary.DictionaryApplication
import com.example.urbandictionary.di.modules.*
import com.example.urbandictionary.ui.DictionaryFragment
import dagger.Component
import javax.inject.Singleton

/**
 * This component allows us to inject our dependencies.
 */
@Singleton
@Component(modules = [
    NetworkModule::class,
    ViewModelModule::class,
    AdapterModule::class,
    AppModule::class,
    DatabaseModule::class
])
interface AppComponent {
    fun inject(application: DictionaryApplication)
    fun inject(fragment: DictionaryFragment)
}