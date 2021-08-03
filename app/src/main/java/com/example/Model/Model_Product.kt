package com.example.application

data class Product_Model
(
    val `data`: List<Data>,
    val limit: Int,
    val skip: Int,
    val total: Int
)