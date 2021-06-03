package com.example.grocery_finder_frontend

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.grocery_finder_frontend.repository.Repository

class MainViewModelFactory(private val repository: Repository): ViewModelProvider.Factory {
    //This class represents viewModel Factory, allowing us to instantiate and return the viewModel object.

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}