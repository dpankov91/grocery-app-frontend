package com.example.grocery_finder_frontend.api

import com.example.grocery_finder_frontend.model.Shop
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    
    @GET("store")
    suspend fun getAllShops(): List<Shop>

    @GET("store/{storeId}")
    suspend fun getShopById(
            @Path("storeId") id: Int
    ): Shop
}