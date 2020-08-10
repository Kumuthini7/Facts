package com.example.facts.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.facts.R

/**
 * Created by Kumuthini.N on 08-08-2020
 */

class FactsActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_facts)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.ll_frameLayout, FactsFragment(), "Facts")
                .commit()
        }
    }
}
