package com.example.urbandictionary

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.example.urbandictionary.di.modules.AppModule
import com.example.urbandictionary.models.DictionaryResponse
import com.example.urbandictionary.network.DictionaryApi
import com.example.urbandictionary.ui.MainActivity
import com.google.gson.Gson
import io.mockk.every
import io.reactivex.Single
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException
import java.io.InputStream
import javax.inject.Inject

const val WAT_WORD = "wat"
class DictionaryFragmentTest {
    @get:Rule
    val testRule: ActivityTestRule<MainActivity>
            = ActivityTestRule(MainActivity::class.java, false, false)

    lateinit var testComponent: TestAppComponent
    lateinit var app: DictionaryApplication
    lateinit var response: DictionaryResponse

    @Inject
    lateinit var dictionaryApi: DictionaryApi

    @Before
    fun setUp() {
        app = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext
                as DictionaryApplication
        testComponent = DaggerTestAppComponent.builder()
            .appModule(AppModule(app))
            .networkModule(TestModule())
            .build()
        app.appComponent = testComponent
        testComponent.inject(this)
        response = Gson().fromJson<DictionaryResponse>(getJsonFromAssets(InstrumentationRegistry
            .getInstrumentation().context.assets.open("WatEntries.json")),
            DictionaryResponse::class.java)
        every { dictionaryApi.searchForWord(WAT_WORD) } returns Single.just(response)
    }

    /**
     * Test initialization for testing
     */
    @Test
    fun testInitialization() {
        testRule.launchActivity(null)
        assertNotNull(dictionaryApi)
        assertNotNull(app)
    }


    fun getJsonFromAssets(inputStream: InputStream): String? {
        val jsonString: String
        jsonString = try {
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, Charsets.UTF_8)
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
        return jsonString
    }
}