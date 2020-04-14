package com.example.urbandictionary

import com.example.urbandictionary.di.daggercomponents.AppComponent
import com.example.urbandictionary.di.modules.*
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class,
    ViewModelModule::class,
    NetworkModule::class,
    DatabaseModule::class,
    AdapterModule::class
])
interface TestAppComponent: AppComponent {
    fun inject(test: DictionaryFragmentTest)
}