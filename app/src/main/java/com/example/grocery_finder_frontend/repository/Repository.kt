package com.example.grocery_finder_frontend.repository

import android.content.Context
import android.util.Log
import com.example.grocery_finder_frontend.api.RetrofitInstance
import com.example.grocery_finder_frontend.model.Book
import com.example.grocery_finder_frontend.model.Shop

class Repository {

    suspend fun getAllShops(): List<Shop> {
        return  RetrofitInstance.api.getAllShops()
    }

    suspend fun getShopById(): Shop {
        return  RetrofitInstance.api.getShopById()
    }

    suspend fun getBooks(): List<Book> {
        return  RetrofitInstance.api.getBook()
    }
}