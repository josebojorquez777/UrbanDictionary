package com.example.urbandictionary.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.urbandictionary.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
                .replace(R.id.container, DictionaryFragment.newInstance())
                .commitNow()
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.options_menu, menu)
//        return true
//    }
}
