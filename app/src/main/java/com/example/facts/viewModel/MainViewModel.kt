package com.example.facts.viewModel

import androidx.lifecycle.ViewModel
import com.example.facts.repo.MainRepository


/**
 * Created by Kumuthini.N on 08-08-2020
 */
class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    fun getFacts() = mainRepository.getFacts()
}


