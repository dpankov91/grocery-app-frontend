package com.example.grocery_finder_frontend.repository

import com.example.grocery_finder_frontend.api.RetrofitInstance
import com.example.grocery_finder_frontend.model.Shop

class Repository {

    suspend fun getAllShops(): List<Shop> {
        return  RetrofitInstance.api.getAllShops()
    }

    suspend fun getShopById(id: Int): Shop {
        return  RetrofitInstance.api.getShopById(id)
    }
}