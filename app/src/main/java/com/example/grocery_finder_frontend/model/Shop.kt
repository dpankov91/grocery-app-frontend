package com.example.grocery_finder_frontend.model

data class Shop (
    val id: Int,
    val name: String,
    val address: String,
    val latitude: Double,
    val longitude: Double,
    val webUrl: String
)