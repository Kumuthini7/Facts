package com.example.facts

import android.app.Application
import com.example.facts.dagger.AppComponent
import com.example.facts.dagger.DaggerAppComponent

/**
 * Created by Kumuthini.N on 08-08-2020
 */
class App : Application() {
  companion object {
    lateinit var component: AppComponent
    lateinit var app: App

  }

  override fun onCreate() {
    super.onCreate()
    app = this
    component = DaggerAppComponent.builder().build()
  }

}
