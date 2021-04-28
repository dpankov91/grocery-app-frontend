package com.example.grocery_finder_frontend.api

import com.example.grocery_finder_frontend.model.Post
import com.example.grocery_finder_frontend.model.Shop
import retrofit2.http.GET

interface Api {
    
    @GET("Store")
    suspend fun getAllShops(): List<Shop>

    @GET("posts/1")
    suspend fun getPost(): Post
}