package com.example.grocery_finder_frontend

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.grocery_finder_frontend.model.Shop
import com.example.grocery_finder_frontend.repository.Repository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository): ViewModel() {

    val allShopsResponse: MutableLiveData<List<Shop>> = MutableLiveData()

    val shopByIdResponse: MutableLiveData<Shop> = MutableLiveData()

    fun getShopById(id: Int) {
        viewModelScope.launch {
            val response = repository.getShopById(id)
            shopByIdResponse.value = response
        }
    }

    fun getAllShops() {
        viewModelScope.launch {
            val response = repository.getAllShops()
            allShopsResponse.value = response
        }
    }
}