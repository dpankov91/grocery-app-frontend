package com.example.grocery_finder_frontend.api

import com.example.grocery_finder_frontend.model.Book
import com.example.grocery_finder_frontend.model.Shop
import retrofit2.http.GET

interface Api {
    
    @GET("store")
    suspend fun getAllShops(): List<Shop>

    @GET("store/1")
    suspend fun getShopById(): Shop

    @GET("author")
    suspend fun getBook(): List<Book>
}