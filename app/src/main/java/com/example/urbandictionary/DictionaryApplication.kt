package com.example.urbandictionary

import android.app.Application
import com.example.urbandictionary.di.daggercomponents.DaggerAppComponent
import com.example.urbandictionary.di.modules.AppModule

/**
 * The Dictionary Application.
 */
class DictionaryApplication: Application(){
    var appComponent = DaggerAppComponent.builder()
        .appModule(AppModule(this))
        .build()
}