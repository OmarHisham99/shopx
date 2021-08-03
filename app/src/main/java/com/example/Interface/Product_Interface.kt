package com.example.Interface
import com.example.application.Category
import com.example.application.Product_Model
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Product_Interface
{
    @GET("products")
    fun getproducts( @Query("name") name:
                        String) : Call<Product_Model?>?
}