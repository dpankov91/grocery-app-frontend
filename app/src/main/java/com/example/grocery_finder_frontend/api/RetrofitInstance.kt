package com.example.grocery_finder_frontend.api

import com.example.grocery_finder_frontend.utils.Constants.Companion.BASE_URL
import com.example.grocery_finder_frontend.utils.Constants.Companion.BASE_URL_OUR
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retrofit by lazy{
        Retrofit.Builder()
            .baseUrl(BASE_URL_OUR)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: Api by lazy {
        retrofit.create(Api::class.java)
    }
}