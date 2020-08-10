package com.example.facts.dagger

import com.example.facts.ui.FactsFragment
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Kumuthini.N on 08-08-2020
 */
@Singleton
@Component(
    modules = [
        AppModule::class,
        ViewModelModule::class,
        RepositoryModule::class]
)
interface AppComponent {
    fun inject(factsFragment: FactsFragment)
}

