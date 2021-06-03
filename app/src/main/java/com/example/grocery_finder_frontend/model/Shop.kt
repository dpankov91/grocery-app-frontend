package com.example.grocery_finder_frontend.model

data class Shop (
    //Retrofit needs an Model class for sending and receiving HTTP Requests.
        // It uses model class for parsing server responses.
    val id: Int,
    val name: String,
    val address: String,
    val latitude: Double,
    val longitude: Double,
    val webUrl: String
)