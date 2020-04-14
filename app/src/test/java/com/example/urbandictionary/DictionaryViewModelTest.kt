package com.example.urbandictionary

import android.app.Application
import com.example.urbandictionary.adapter.DictionaryRecyclerAdapter
import com.example.urbandictionary.models.DictionaryResponse
import com.example.urbandictionary.models.Repository
import com.example.urbandictionary.viewmodel.DictionaryViewModel
import com.google.gson.Gson
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.SpyK
import io.mockk.just
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.doNothing

const val WAT = "wat"
class DictionaryViewModelTest {
    lateinit var dictionaryViewModel: DictionaryViewModel
    lateinit var response: DictionaryResponse
    @MockK
    lateinit var repository: Repository
    @MockK
    lateinit var app: Application
//    @MockK
    @SpyK
    var dictionaryAdapter: DictionaryRecyclerAdapter = DictionaryRecyclerAdapter(mutableListOf(), {}, R.layout.layout_dictionary_entry)
    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        dictionaryViewModel = DictionaryViewModel(repository, app, dictionaryAdapter)
        response = Gson().fromJson<DictionaryResponse>(this.javaClass.classLoader
            ?.getResource("WatEntries.json")?.readText(), DictionaryResponse::class.java)
    }

    @Test
    fun `given successful response then populate observable data`() {
        every { repository.searchForWord(WAT) } returns Single.just(response.list)
        every { repository.addPreviouslySearchedWord(WAT) } returns Unit
        every { repository.saveToLocalCache(response.list) } returns Unit
        every { dictionaryAdapter.notifyDataSetChanged() } returns Unit
        dictionaryViewModel.slangWord.set(WAT)
        dictionaryViewModel.searchWord()
        assertEquals("The only [proper] [response] to something that makes absolutely [no sense].",
            dictionaryViewModel.adapter.get()?.entries?.get(0)?.definition
        )
    }
}