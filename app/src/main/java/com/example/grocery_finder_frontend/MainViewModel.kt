package com.example.grocery_finder_frontend

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.grocery_finder_frontend.model.Post
import com.example.grocery_finder_frontend.model.Shop
import com.example.grocery_finder_frontend.repository.Repository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository): ViewModel() {

    val myResponse: MutableLiveData<List<Shop>> = MutableLiveData()

    /*fun getPost() {
        viewModelScope.launch {
            val response = repository.getPost()
            myResponse.value = response
        }
    }*/

    fun getAllShops() {
        viewModelScope.launch {
            val response = repository.getAllShops()
            myResponse.value = response
        }
    }
}