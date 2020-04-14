package com.example.urbandictionary.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.urbandictionary.viewmodel.DictionaryViewModel
import com.example.urbandictionary.viewmodel.ViewModelFactory
import com.example.urbandictionary.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * This module supplies the needed dependencies for view models for dependency injection.
 */
@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(DictionaryViewModel::class)
    internal abstract fun bindDictionaryViewModel(viewModel: DictionaryViewModel): ViewModel
}