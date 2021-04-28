package com.example.grocery_finder_frontend.repository

import com.example.grocery_finder_frontend.api.RetrofitInstance
import com.example.grocery_finder_frontend.model.Post
import com.example.grocery_finder_frontend.model.Shop

class Repository {

    suspend fun getAllShops(): List<Shop> {
        return  RetrofitInstance.api.getAllShops()
    }

    suspend fun getPost(): Post {
        return  RetrofitInstance.api.getPost()
    }
}