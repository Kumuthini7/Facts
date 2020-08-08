package com.example.facts.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.facts.repo.MainRepository
import javax.inject.Inject

/**
 * Created by Kumuthini.N on 08-08-2020
 */

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory @Inject constructor(private val mainRepository: MainRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(mainRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

