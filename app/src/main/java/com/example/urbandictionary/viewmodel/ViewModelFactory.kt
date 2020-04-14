package com.example.urbandictionary.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

/**
 * This view model factory is used for the creation of view models.
 */
@Suppress("UNCHECKED_CAST")
class ViewModelFactory @Inject constructor(
    private val map: Map<Class<out ViewModel>,
    @JvmSuppressWildcards Provider<ViewModel>>): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return map[modelClass]?.get() as T
    }
}