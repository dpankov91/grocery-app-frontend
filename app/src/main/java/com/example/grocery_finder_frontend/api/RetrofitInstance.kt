package com.example.grocery_finder_frontend.api

import com.example.grocery_finder_frontend.utils.Constants.Companion.BASE_URL_GROCERY
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    //This is an instance of Retrofit. Here we specify the base url and transform usable data.
    //Lazy = Lazy initialization
    private val retrofit by lazy{
        Retrofit.Builder()//Invoke Retrofit
            .baseUrl(BASE_URL_GROCERY)//Set the base url
            .addConverterFactory(GsonConverterFactory.create())//Convert Json response to useful data.
            .build()//Usable data.
    }

    val api: Api by lazy {
        retrofit.create(Api::class.java)
    }
}