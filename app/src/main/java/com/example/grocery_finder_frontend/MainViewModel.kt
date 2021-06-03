package com.example.grocery_finder_frontend

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.grocery_finder_frontend.model.Shop
import com.example.grocery_finder_frontend.repository.Repository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository): ViewModel() {
    //ViewModel interacts with model and prepares observable that can be seen by a View,
    //This model is designed to store and manage UI-related data in a lifecycle conscious way.
    //It should not be aware of the view the user is interacting with.
    //Repository is called from within here.
    val allShopsResponse: MutableLiveData<List<Shop>> = MutableLiveData()//Empty MutableLivedata variables.

    val shopByIdResponse: MutableLiveData<Shop> = MutableLiveData()

    fun getShopById(id: Int) {
        viewModelScope.launch {
            val response = repository.getShopById(id)
            shopByIdResponse.value = response
        }
    }

    fun getAllShops() {
        viewModelScope.launch {
            val response = repository.getAllShops() //Result of the invoked repository function is stored in response value.
            allShopsResponse.value = response //Result of the function is stored in mutableLiveData response.
        }
    }
}