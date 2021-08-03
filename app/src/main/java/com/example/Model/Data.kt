package com.example.application

data class Data(
    val categories: List<Category>,
    val createdAt: String,
    val description: String,
    val id: Int,
    val image: String,
    val manufacturer: String,
    val model: String,
    val name: String,
    val price: Double,
    val shipping: Int,
    val type: String,
    val upc: String,
    val updatedAt: String,
    val url: String
)