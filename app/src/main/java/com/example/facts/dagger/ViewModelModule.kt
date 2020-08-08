package com.example.facts.dagger

import com.example.facts.repo.MainRepository
import com.example.facts.viewModel.MainViewModelFactory
import dagger.Module
import dagger.Provides

/**
 * Created by Kumuthini.N on 08-08-2020
 */
@Module
class ViewModelModule {

    @Provides
    fun providesMainViewModelFactory(mainRepository: MainRepository): MainViewModelFactory {
        return MainViewModelFactory(mainRepository)
    }
}

