package com.example.grocery_finder_frontend

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.grocery_finder_frontend.model.Book
import com.example.grocery_finder_frontend.model.Shop
import com.example.grocery_finder_frontend.repository.Repository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository): ViewModel() {

    val allShopsResponse: MutableLiveData<List<Shop>> = MutableLiveData()

    val shopByIdResponse: MutableLiveData<Shop> = MutableLiveData()

    val bookResponse: MutableLiveData<List<Book>> = MutableLiveData()

    fun getShopById() {
        viewModelScope.launch {
            val response = repository.getShopById()
            shopByIdResponse.value = response
        }
    }

    fun getAllShops() {
        viewModelScope.launch {
            val response = repository.getAllShops()
            allShopsResponse.value = response
        }
    }

    fun getBooks() {
        viewModelScope.launch {
            val response = repository.getBooks()
            bookResponse.value = response
        }
    }
}